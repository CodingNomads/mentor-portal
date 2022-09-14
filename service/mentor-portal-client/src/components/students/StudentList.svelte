<script>
    import { onMount, onDestroy } from "svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";
    import NavBar from "../../components/NavBar.svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";
    import UpdateCourseMentorship from "../formInputs/UpdateCourseMentorship.svelte";
    import Icon from 'svelte-awesome';
    import { flag } from "svelte-awesome/icons";

    export let studentList = [];
    export let filteredStudents = [];

    // authorization
    const authToken = sessionStorage.getItem("authToken")
    const isAdmin = sessionStorage.getItem("isAdmin")

    // edit variables
    let triggerContent;
    let triggerId;
    let courseTrackLabel = "Update Course";
    let mentorShipOptionLabel = "Update Mentorship";

    const searchStudents = (e) => {
        let searchString = e.target.value.toLowerCase();
         filteredStudents = studentList.filter(student => {
                let firstName = student.firstName.toLowerCase()
                let lastName = student.lastName.toLowerCase()
                let fullName = student.firstName.toLowerCase() + " " + student.lastName.toLowerCase()
            return firstName.includes(searchString) || lastName.includes(searchString) || fullName.includes(searchString)
        });
    };

    function updateCourseTrack(studentId, content) {
        triggerId = studentId
        triggerContent = content
        return triggerId && triggerContent
    };

    const resetUpdateCourseTrack = () => {
        triggerId = 0
        triggerContent = ""
        return triggerId && triggerContent
    };

    function updateMentorshipOption(studentId, content) {
        triggerId = studentId
        triggerContent = content
        return triggerId && triggerContent
    };

    const resetUpdateMentorshipOption = () => {
        triggerId = 0
        triggerContent = ""
        return triggerId && triggerContent
    };

    onMount(async () => {
        const url = API_BASE_URL + "/api/students"
        studentList = await authorizedApiGetCall(authToken, url)
        filteredStudents = studentList
        }
    )

    onDestroy(studentList, filteredStudents, triggerId, triggerContent)
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
            <div class="column is-5">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchStudents} placeholder="Enter student name." />
            </div>
        </div>
    </div>
    <br>
    <!-- student table -->
    <table class='table is-fullwidth is-bordered'>
        <thead>
            <tr>
                <th>fullName</th>
                <th>courseTrack</th>
                <th>mentorshipOption</th>
                <th>assignedMentor</th>
            </tr>
        </thead>
        <tbody >
        {#each filteredStudents as student}
            <tr>
                <td>
                    {#if student.flag}
                        <div class="row">
                            <div class="columns">
                                <div class="column is-1">
                                    <Icon data={flag} style="color:red"/>
                                </div>
                                <div class="column">
                                    <a href="/students/{student.id}" class="is-link is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                                </div>
                            </div>
                        </div>
                    {:else}
                        <div class="row">
                            <div class="columns">
                                <div class="column is-1">
                                    <!-- spacer -->
                                </div>
                                <div class="column">
                                    <a href="/students/{student.id}" class="is-link is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                                </div>
                            </div>
                        </div>
                    {/if}
                </td>
                <td>
                    <span class="is-small" on:click={updateCourseTrack(student.id, student.courseTrack)}>{student.courseTrack}</span>
                    {#if triggerId === student.id && triggerContent === student.courseTrack && isAdmin === "true"}
                        <div class="row">
                            <UpdateCourseMentorship label={courseTrackLabel} userId={student.id} />
                            <button class='button is-small' on:click={resetUpdateCourseTrack}>Cancel</button>
                        </div>
                    {/if}
                </td>
                <td>
                    {#if student.mentorshipOption}
                        <span class="is-small" on:click={updateMentorshipOption(student.id, student.mentorshipOption)}>{student.mentorshipOption}</span>
                        {#if triggerId == student.id && triggerContent === student.mentorshipOption && isAdmin === "true"}
                            <div class="row">
                                <UpdateCourseMentorship label={mentorShipOptionLabel} userId={student.id} />
                                <button class='button is-small' on:click={resetUpdateMentorshipOption}>Cancel</button>
                            </div>
                        {/if}
                    {:else}
                        None
                    {/if}
                </td>
                <td>
                    {#if student.assignedMentors.length > 0}
                        {#each student.assignedMentors as mentor}
                            <a class="is-link is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName[0]}. </a>
                            <br>
                        {/each}
                    {:else}
                        <AssignMentor bind:student={student} />
                    {/if}
                </td>
            </tr>    
        {/each}
        </tbody>
    </table>
</div>
