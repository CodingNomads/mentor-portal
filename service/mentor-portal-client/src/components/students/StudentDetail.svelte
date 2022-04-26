<script>
    import { onMount, onDestroy } from "svelte";
    import { navOptions } from "./StudentNav.svelte";
    import NavBar from "../NavBar.svelte";
    // nav variables
    let selected = navOptions[0];
    let intSelected = 0;
    // nav change function
    function changeComponent(event) {
        selected = navOptions[event.srcElement.id];
        intSelected = event.srcElement.id;
    };
    // student variables
    export let studentId;
    export let student;

    onMount(async() => {
        const url = API_BASE_URL + `/api/students/${studentId}`
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const response = await fetch(url, {
            headers,
            mode: 'cors',
            credentials: "same-origin"
        })
        student = await response.json()
    });

onDestroy(student, studentId)
</script>

<div class="container">
    <NavBar />
    {#if student}
    <br>
    <div class="card-content">
        <!-- title row -->
        <div class="row">
            <div class="column is-7">
                <h1 class="title is-1"><strong>{student.firstName} {student.lastName} ({student.forumUsername})</strong></h1>
                <p><progress class="progress is-info" value=10 max=10></progress></p> 
            </div>
        </div>
        <!-- nav row -->
        <br>
        <div class="row">
            {#each navOptions as option, i}
                <a class={intSelected==i ? "button is-small is-info active p-2 ml-1" : "p-2 ml-1 button is-light is-small"} on:click={changeComponent} id={i} role="tab">{option.page}</a>
            {/each}
        </div>
        <!-- selected nav row -->
        <br>
        <div class="row">
            <svelte:component this={selected.component} student={student}/>
        </div>
    </div>
    {/if}
</div>