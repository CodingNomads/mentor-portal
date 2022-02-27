<script>
    import NavBar from "../../components/NavBar.svelte";
    import { onMount, onDestroy } from "svelte";
    import MentorDropdown from "../formInputs/MentorDropdown.svelte";

    export let studentList = [];
    let filteredStudents = [];
    let selectedMentor;

    const searchStudents = (e) => {
        const searchString = e.target.value
        filteredStudents = studentList.filter(student => {
           return student.firstName.includes(searchString) || student.lastName.includes(searchString)
        });
        console.log(filteredStudents)
    };

    const assignMentor = (selectedMentor) => {

    };

    onMount(async () => {
        const response = await fetch("http://localhost:8080/api/students", {
            mode: 'cors',
            credentials: "same-origin"
        })
        studentList = await response.json()
        console.log(studentList)
    })

    onDestroy(studentList, filteredStudents)
</script>

<div class="container">
    <NavBar />
    <br>
    <!-- title and search box -->
    <div class="row">
        <div class="columns">
            <div class="column is-one-fifth">
                <h1 class="title is-1"><strong>Students</strong></h1>
            </div>
            <div class="column is-two-fifths">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchStudents} placeholder="Search Student" />
            </div>
        </div>
    </div>
    <br>
    <!-- student list -->
    {#if filteredStudents.length > 0}
        {#each filteredStudents as student (student.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-2 is-offset-one-fifth">
                        <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        <!-- iterate through course track -->
                        <div class="row">
                            <span class="tag is-dark">python</span>
                            <span class="tag is-dark">java</span>
                        </div>
                    </div>
                    {#if student.assignedMentor}
                    <div class="column is-offset-1">
                        <p><strong>Mentor: </strong>{student.assignedMentor}</p>
                    </div>
                    {:else}
                    <div class="column is-offset-1">
                        <MentorDropdown label="Assign: " bind:value={selectedMentor} />
                    </div>
                    {/if}
                </div>
            </div>
            <div class="column is-three-fifths is-offset-one-fifth">
                <hr>
            </div>
        {/each}
    {:else}
        {#each studentList as student (student.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-2 is-offset-one-fifth">
                        <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        <!-- iterate through course track -->
                        <div class="row">
                            <span class="tag is-dark">python</span>
                            <span class="tag is-dark">java</span>
                        </div>
                    </div>
                    {#if student.assignedMentor}
                    <div class="column is-offset-1">
                        <p><strong>Mentor: </strong>{student.assignedMentor}</p>
                    </div>
                    {:else}
                    <div class="column is-offset-1">
                        <MentorDropdown label="Assign: " bind:value={selectedMentor} />
                    </div>
                    {/if}
                </div>
            </div> 
            <div class="column is-three-fifths is-offset-one-fifth">
                <hr>
            </div>
        {/each}
    {/if}
</div>
