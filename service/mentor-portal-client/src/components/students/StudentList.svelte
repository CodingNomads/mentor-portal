<script>
    import { onMount, onDestroy } from "svelte";
    import { userCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import NavBar from "../../components/NavBar.svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";
    import UpdateCourseMentorship from "../formInputs/UpdateCourseMentorship.svelte";

    export let studentList = [];
    let filteredStudents = [];

    // edit variables
    let triggerContent;
    let triggerId;
    let courseTrackLabel = "updatedCourseTrack";
    let mentorShipOptionLabel = "updatedMentorshipOption";

    const searchStudents = (e) => {
        const searchString = e.target.value
        filteredStudents = studentList.filter(student => {
           return student.firstName.includes(searchString) || student.lastName.includes(searchString)
        });
    };

    function updateCourseTrack(studentId, content) {
        triggerId = studentId
        triggerContent = content
        console.log(`${triggerId}`)
        console.log(`${studentId}`)
        return triggerId && triggerContent
    };

    const resetUpdateCourseTrack = () => {
        console.log("remove edit")
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
        const response = await fetch(url, {
            mode: 'cors',
            credentials: "same-origin"
        })
        studentList = await response.json()
        // console.log(studentList)
    })

    onDestroy(studentList, filteredStudents, triggerId)
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
                <!-- icon column -->
                <div class="columns">
                    <div class="column is-offset-1 is-2">
                        <div class="row">
                            <Icon data={userCircle} scale={5} />
                        </div>
                    </div>
                    <!-- name, courseTrack, mentorshipOption, and flag column -->
                    <div class="column is-3">
                        <div class="row">
                            <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        </div>
                        <br>
                        <!-- courseTrack and mentorshipOption -->
                        <div class="row">
                            <div class="tags has-addons" id={student.id}>
                                <div class="row">
                                    <span class="tag">courseTrack</span>
                                    <span class="tag is-dark is-small" on:mouseover={updateCourseTrack(student.id, student.courseTrack)} on:focus={updateCourseTrack(student.id, student.courseTrack)}>{student.courseTrack}</span>
                                </div>
                                {#if triggerId === student.id && triggerContent === student.courseTrack}
                                    <div class="row" on:mouseleave={resetUpdateCourseTrack}>
                                        <UpdateCourseMentorship label={courseTrackLabel} userId={student.id} />
                                    </div>
                                {/if}
                                {#if student.mentorshipOption}
                                <div class="row">
                                    <span class="tag">mentorshipOption</span>
                                    <span class="tag is-small is-dark" on:mouseover={updateMentorshipOption(student.id, student.mentorshipOption)} on:focus={updateMentorshipOption(student.id, student.mentorshipOption)}>{student.mentorshipOption}</span>
                                </div>
                                    {#if triggerId == student.id && triggerContent === student.mentorshipOption}
                                        <div class="row" on:mouseleave={resetUpdateMentorshipOption}>
                                            <UpdateCourseMentorship label={mentorShipOptionLabel} userId={student.id} />
                                        </div>
                                    {/if}
                                {/if}
                            </div>
                        </div>
                        {#if student.flag === true }
                        <br>
                        <br>
                        <span class="tag is-danger">Flagged</span>
                        {/if}
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-1">
                        <div class="row">
                            <p>
                                <strong>assignedMentors: </strong>
                                {#each student.assignedMentors as mentor}
                                    <ul><a class="is-link is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                                {/each}
                            </p>
                        </div>
                    </div>
                    {:else}
                    <div class="column is-offset-1 is-one-quarter">
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
                    <!-- icon column -->
                    <div class="column is-offset-1 is-2">
                        <div class="row ">
                            <div class="row">
                                <Icon data={userCircle} scale={5} />
                            </div>
                        </div>
                    </div>
                    <!-- name, courseTrack, mentorshipOption, and flag column -->
                    <div class="column is-3">
                        <div class="row">
                            <a href="/students/{student.id}" class="button is-info is-small"><strong>{student.firstName} {student.lastName}</strong></a>
                        </div>     
                        <br>                   
                        <!-- courseTrack and mentorshipOption -->
                        <div class="row">
                            <div class="tags has-addons" id={student.id}>
                                <div class="row">
                                    <span class="tag">courseTrack</span>
                                    <span class="tag is-dark is-small" on:mouseover={updateCourseTrack(student.id, student.courseTrack)} on:focus={updateCourseTrack(student.id, student.courseTrack)}>{student.courseTrack}</span>
                                </div>
                                {#if triggerId === student.id && triggerContent === student.courseTrack}
                                    <div class="row" on:mouseleave={resetUpdateCourseTrack}>
                                        <UpdateCourseMentorship label={courseTrackLabel} userId={student.id} />
                                    </div>
                                {/if}
                                {#if student.mentorshipOption}
                                <div class="row">
                                    <span class="tag">mentorshipOption</span>
                                    <span class="tag is-small is-dark" on:mouseover={updateMentorshipOption(student.id, student.mentorshipOption)} on:focus={updateMentorshipOption(student.id, student.mentorshipOption)}>{student.mentorshipOption}</span>
                                </div>
                                    {#if triggerId === student.id && triggerContent === student.mentorshipOption}
                                        <div class="row" on:mouseleave={resetUpdateMentorshipOption}>
                                            <UpdateCourseMentorship label={mentorShipOptionLabel} userId={student.id} />
                                        </div>
                                    {/if}
                                {/if}
                            </div>
                        </div>
                        {#if student.flag === true }
                        <br>
                        <br>
                        <span class="tag is-danger">Flagged</span>
                        {/if}
                    </div>
                    {#if student.assignedMentors.length > 0}
                    <div class="column is-offset-1">
                        <div class="row">
                            <p>
                                <strong>assignedMentors: </strong>
                                {#each student.assignedMentors as mentor}
                                    <ul><a class="is-link is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName}</a></ul>
                                {/each}
                            </p>
                        </div>
                    </div>
                    {:else}
                    <div class="column is-offset-1 is-one-quarter">
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
