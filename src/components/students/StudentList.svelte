<script>
    import NavBar from "../../components/NavBar.svelte";
    import { onMount, onDestroy } from "svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";

    export let studentList = [];
    let filteredStudents = [];

    const searchStudents = (e) => {
        const searchString = e.target.value
        filteredStudents = studentList.filter(student => {
           return student.firstName.includes(searchString) || student.lastName.includes(searchString)
        });
        console.log(filteredStudents)
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
            <div class="column is-4">
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
                            <span class="tag is-dark">{student.courseTrack}</span>
                        </div>
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-2 is-2">
                        <p><strong>assignedMentor: </strong></p>
                    </div>
                    <div class="column is-2">
                        {#each student.assignedMentors as mentor}
                            <div class="row">
                                <ul><a class="button is-link is-small is-light" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                            </div>
                        {/each}
                    </div>
                    {:else}
                    <div class="column is-offset-2 is-one-quarter">
                        <AssignMentor bind:student={student} />
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
                            <span class="tag is-dark">{student.courseTrack}</span>
                        </div>
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-2 is-2">
                        <p><strong>assignedMentor: </strong></p>
                    </div>
                    <div class="column is-2">
                        {#each student.assignedMentors as mentor}
                            <div class="row">
                                <ul><a class="button is-link is-small is-light" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                            </div>
                        {/each}
                    </div>
                    {:else}
                    <div class="column is-offset-2 is-one-quarter">
                        <AssignMentor bind:student={student} />
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
