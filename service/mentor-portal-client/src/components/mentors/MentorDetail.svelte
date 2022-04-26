<script>
    import { onMount, onDestroy } from "svelte";
    import NavBar from "../NavBar.svelte";
    import { navOptions } from "./MentorNav.svelte";

    // nav variables
    let selected = navOptions[0];
    let intSelected = 0;
    // nav functions
    function changeComponent(event) {
        selected = navOptions[event.srcElement.id];
        intSelected = event.srcElement.id;
    };
    // mentor variables
    export let mentor;
    export let mentorId;

    onMount(async() => {
        const url = API_BASE_URL + `/api/mentors/${mentorId}`
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const response = await fetch(url, {
            headers,
            mode: 'cors',
            credentials: "same-origin"
        })
        
        mentor = await response.json()
    });
    
    onDestroy(mentor);
</script>

<div class="container">
    <NavBar />
    {#if mentor}
    <br>
    <div class="card-content">
        <!-- container for title and progress -->
        <div class="row">
            <div class="column is-7">
                <h1 class="title is-1"><strong>{mentor.firstName} {mentor.lastName} ({mentor.forumUsername})</strong></h1>
                <!-- progress bar: value=studentCount, max=maxStudent -->
                <p><progress class="progress is-info" value={mentor.studentCount} max={mentor.maxStudents}>4</progress>{mentor.studentCount}/{mentor.maxStudents} students</p>
            </div>
        </div>
        <!-- container for buttons -->
        <br>
        <div class="row">
            {#each navOptions as option, i}
                <a class={intSelected==i ? "button is-small is-info active p-2 ml-1" : "p-2 ml-1 button is-light is-small"} on:click={changeComponent} id={i} role="tab">{option.page}</a>
            {/each}
        </div>
        <!--container for selected nav  -->
        <br>
        <div class="row">
            <svelte:component this={selected.component} mentor={mentor}/>
        </div>
    </div>
    {:else}
    <p class="alert is-warning">Mentor Detail Page: Didn't load :/</p>
    {/if}
</div>
