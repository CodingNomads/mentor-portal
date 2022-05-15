<script>
    import { onMount, onDestroy } from "svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";
    import NavBar from "../../components/NavBar.svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";
    import UpdateCourseMentorship from "../formInputs/UpdateCourseMentorship.svelte";

    export let studentList = [];
    export let filteredStudents = [];

    // authorization
    const authToken = localStorage.getItem("authToken")
    const isAdmin = localStorage.getItem("isAdmin")

    // edit variables
    let triggerContent;
    let triggerId;
    let courseTrackLabel = "updatedCourseTrack";
    let mentorShipOptionLabel = "updatedMentorshipOption";

    const searchStudents = (e) => {
        let searchString = e.target.value
        filteredStudents = studentList.filter(student => {
            let firstName = student.firstName
            let lastName = student.lastName
            let fullName = student.firstName + " " + student.lastName
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
    })

    onDestroy(studentList, filteredStudents, triggerId)
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
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchStudents} placeholder="spacebar + backspace to populate" />
            </div>
        </div>
    </div>
    <br>
    <!-- filtered student list -->
    {#if filteredStudents.length > 0}
        {#each filteredStudents as student (student.id)}
            <div class="row">
                <div class="columns">
                    <!-- name, courseTrack, mentorshipOption, and flag column -->
                    <div class="column is-3 is-offset-one-fifth">
                        <div class="row">
                            <a href="/students/{student.id}" class="button is-link is-medium"><strong>{student.firstName} {student.lastName}</strong></a>
                        </div>
                        <!-- courseTrack and mentorshipOption -->
                        <div class="row">
                            <div class="tags has-addons" id={student.id}>
                                <div class="row">
                                    <span class="tag">courseTrack</span>
                                    <span class="tag is-dark is-small" on:mouseover={updateCourseTrack(student.id, student.courseTrack)} on:focus={updateCourseTrack(student.id, student.courseTrack)}>{student.courseTrack}</span>
                                {#if triggerId === student.id && triggerContent === student.courseTrack}
                                    <div class="row" on:mouseleave={resetUpdateCourseTrack}>
                                        <UpdateCourseMentorship label={courseTrackLabel} userId={student.id} />
                                    </div>
                                {/if}
                                </div>
                                <div class="row">
                                {#if student.mentorshipOption}
                                    <span class="tag">mentorshipOption</span>
                                    <span class="tag is-small is-dark" on:mouseover={updateMentorshipOption(student.id, student.mentorshipOption)} on:focus={updateMentorshipOption(student.id, student.mentorshipOption)}>{student.mentorshipOption}</span>
                                    {#if triggerId == student.id && triggerContent === student.mentorshipOption}
                                        <div class="row" on:mouseleave={resetUpdateMentorshipOption}>
                                            <UpdateCourseMentorship label={mentorShipOptionLabel} userId={student.id} />
                                        </div>
                                    {/if}
                                {/if}
                                </div>
                            </div>
                        </div>
                        {#if student.flag === true }
                        <br>
                        <span class="tag is-danger">Flagged</span>
                        {/if}
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-2">
                        <div class="row">
                            {#each student.assignedMentors as mentor}
                            <div class="tags has-addons">
                                <span class="tag">assignedMentor</span>
                                <a class="tag is-link" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a>
                            </div>
                            {/each}
                        </div>
                    </div>
                    {:else}
                        {#if isAdmin}
                        <div class="column is-offset-2 is-one-quarter">
                            <AssignMentor bind:student={student} />
                        </div>
                        {/if}
                    {/if}
                </div>
            </div>
            <div class="column is-two-thirds is-offset-2">
                <hr>
            </div>
        {/each}
    {/if}
</div>
