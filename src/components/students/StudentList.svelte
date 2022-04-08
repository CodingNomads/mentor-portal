<script>
    import { onMount, onDestroy } from "svelte";
    import { userCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import NavBar from "../../components/NavBar.svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";
    import UpdateFlag from "../formInputs/UpdateFlag.svelte";

    export let studentList = [];
    let filteredStudents = [];
    let falseFlag = false
    let trueFlag = true

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
            <div class="column is-2">
                <h1 class="title is-1"><strong>Students</strong></h1>
            </div>
            <div class="column is-4">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchStudents} placeholder="Search Student" />
            </div>
        </div>
    </div>
    <br>
    <!-- filtered student list -->
    {#if filteredStudents.length > 0}
        {#each filteredStudents as student (student.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-offset-1 is-2">
                        <div class="row">
                            <Icon data={userCircle} scale={5} />
                        </div>
                    </div>
                    <div class="column is-2">
                        <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        <!-- iterate through course track -->
                        <div class="row">
                            <span class="tag is-dark">{student.courseTrack}</span>
                        </div>
                        <br>
                        <br>
                        {#if student.flag === true }
                        <span class="tag is-danger">Flagged</span>
                        {/if}
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-2">
                        <div class="row">
                            <p>
                                <strong>assignedMentor: </strong>
                                {#each student.assignedMentors as mentor}
                                    <ul><a class="button is-link is-light is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                                {/each}
                            </p>
                        </div>
                    </div>
                    {:else}
                    <div class="column is-offset-2 is-one-quarter">
                        <AssignMentor bind:student={student} />
                    </div>
                    {/if}
                </div>
            </div>
            <div class="column is-two-thirds is-offset-2">
                <hr>
            </div>
        {/each}
    {:else}
        <!-- normal student list from DB -->
        {#each studentList as student (student.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-offset-1 is-2">
                        <div class="row ">
                            <div class="row">
                                <Icon data={userCircle} scale={5} />
                            </div>
                        </div>
                    </div>
                    <div class="column is-2">
                        <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        <!-- iterate through course track -->
                        <div class="row">
                            <span class="tag is-dark">{student.courseTrack}</span>
                        </div>
                        <br>
                        <br>
                        {#if student.flag === true }
                        <span class="tag is-danger">Flagged</span>
                        {/if}
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-2">
                        <div class="row">
                            <p>
                                <strong>assignedMentor: </strong>
                                {#each student.assignedMentors as mentor}
                                    <ul><a class="button is-link is-light is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                                {/each}
                            </p>
                        </div>
                    </div>
                    {:else}
                    <div class="column is-offset-2 is-one-quarter">
                        <AssignMentor bind:student={student} />
                    </div>
                    {/if}
                </div>
            </div> 
            <div class="column is-two-thirds is-offset-2">
                <hr>
            </div>
        {/each}
    {/if}
</div>
