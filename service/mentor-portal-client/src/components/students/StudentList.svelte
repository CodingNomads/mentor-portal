<script>
    import { onMount, onDestroy } from "svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";
    import NavBar from "../../components/NavBar.svelte";
    import AssignMentor from "../formInputs/AssignMentor.svelte";
    import UpdateCourseMentorship from "../formInputs/UpdateCourseMentorship.svelte";
    import UpdateStatus from "../formInputs/UpdateStatus.svelte";
    import Icon from 'svelte-awesome';
    import { flag, pencil } from "svelte-awesome/icons";
    import UpdateDate from "../formInputs/UpdateDate.svelte";

    export let studentList = [];
    export let filteredStudents = [];

    // authorization
    const authToken = sessionStorage.getItem("authToken")
    const isAdmin = sessionStorage.getItem("isAdmin")

    // edit variables
    let selectedFilter;
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

    const allFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            return student
        });
        selectedFilter = "All";
    }

    const activeFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const activeStatus = "active";
            return student.statusDescription.toLowerCase().includes(activeStatus)
        });
        selectedFilter = "Active"
    }

    const pausedFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const pausedStatus = "paused";
            return student.statusDescription.toLowerCase().includes(pausedStatus)
        });
        selectedFilter = "Paused";
    }

    const cancelledFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const cancelledStatus = "cancelled";
            return student.statusDescription.toLowerCase().includes(cancelledStatus)
        });
        selectedFilter = "Cancelled";
    }

    const compProgramFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const compProgramStatus = "completed program";
            return student.statusDescription.toLowerCase().includes(compProgramStatus)
        });
        selectedFilter = "Completed Program"; 
    }

    const compCurriculumFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const compCurriculumStatus = "completed curriculum";
            return student.statusDescription.toLowerCase().includes(compCurriculumStatus)
        });
        selectedFilter = "Completed Curriculum";
    }

    const droppedFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const droppedStatus = "dropped out";
            return student.statusDescription.toLowerCase().includes(droppedStatus)
        });
        selectedFilter = "Dropped Out."
    }

    const miaFilter = (e) => {
        filteredStudents = studentList.filter(student => {
            const miaStatus = "mia";
            return student.statusDescription.toLowerCase().includes(miaStatus)
        });
        selectedFilter = "MIA";
    }

    function cancelEdit() {
        triggerId = 0;
        triggerContent = "";
        return triggerId && triggerContent
    }

    function editStart(studentId, labelTitle) {
        triggerId = studentId;
        triggerContent = labelTitle;
        return triggerId && triggerContent
    }

    function editEnd(studentId, labelTitle) {
        triggerId = studentId;
        triggerContent = labelTitle;
        return triggerId && triggerContent
    }

    function updateStatus(studentId, content) {
        triggerId = studentId;
        triggerContent = content;
        return triggerId && triggerContent
    };

    function updateCourseTrack(studentId, content) {
        triggerId = studentId
        triggerContent = content
        return triggerId && triggerContent
    };

    function updateMentorshipOption(studentId, content) {
        triggerId = studentId
        triggerContent = content
        return triggerId && triggerContent
    };

    onMount(async () => {
        const url = API_BASE_URL + "/api/students"
        studentList = await authorizedApiGetCall(authToken, url)
        studentList.sort((a, b) => a.firstName.localeCompare(b.firstName))
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
    <!-- student status filter -->
    <div class="row">
        <button id="allFilter" class="button is-light" on:click={allFilter}>All</button>
        <button id="activeFilter" class="button is-light is-primary" on:click={activeFilter}>Active</button>
        <button id="pausedFilter" class="button is-light is-warning" on:click={pausedFilter}>Paused</button>
        <button id="cancelledFilter" class="button is-light is-danger" on:click={cancelledFilter}>Cancelled</button>
        <button id="compProgramFilter" class="button is-light is-info" on:click={compProgramFilter}>Completed Program</button>
        <button id="compCurriculumFilter" class="button is-light is-success" on:click={compCurriculumFilter}>Completed Curriculum</button>
        <button id="droppedFilter" class="button is-light" on:click={droppedFilter}>Dropped Out</button>
        <button id="miaFilter" class="button is-light" on:click={miaFilter}>MIA</button>
    </div>
    <br>
    <!-- student table -->
    <table class='table is-fullwidth is-bordered'>
        <thead>
            <tr>
                <th>Full Name</th>
                <th>Student Status</th>
                <th>Course Track</th>
                <th>Mentorship Option</th>
                <th>Program Start</th>
                <th>Program End</th>
                <th>Assigned Mentors</th>
            </tr>
        </thead>
        <tbody >
        {#if filteredStudents.length === 0}
            <tr>
                <div class="tile is-light">
                    <p class="title">
                        No students returned with the status: <em>{selectedFilter}</em>.
                    </p>
                </div>
            </tr>
        {:else}
            {#each filteredStudents as student}
            <tr>
                <!-- name -->
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
                <!-- studentStatus -->
                <td>
                    <div class="row">
                        <div class="columns">
                            <!-- edit icon -->
                            <div class="column is-1">
                                <span class="is-small" on:click={updateStatus(student.id, student.statusDescription)}>
                                    <Icon data={pencil} />
                                </span>
                            </div>
                            <div class="column">
                                {student.statusDescription}
                            </div>
                        </div>
                    </div>
                    {#if triggerId === student.id && triggerId != 0 && triggerContent === student.statusDescription && isAdmin === "true"}
                        <div class="row">
                            <UpdateStatus userId={student.id} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        </div>
                    {/if}
                </td>
                <!-- courseTrack -->
                <td>
                    <div class="row">
                        <div class="columns">
                            <!-- edit icon -->
                            <div class="column is-1">
                                <span class="is-small" on:click={updateCourseTrack(student.id, student.courseTrack)}>
                                    <Icon data={pencil} />
                                </span>
                            </div>
                            <!-- courseTrack title -->
                            <div class="column">
                                {student.courseTrack}
                            </div>
                        </div>
                    </div>
                    {#if triggerId === student.id && triggerId != 0 && triggerContent === student.courseTrack && isAdmin === "true"}
                        <div class="row">
                            <UpdateCourseMentorship label={courseTrackLabel} userId={student.id} />
                            <button class='button is-small' on:click={cancelEdit}>Cancel</button>
                        </div>
                    {/if}
                </td>
                <!-- mentorshipOption -->
                <td>
                    <!-- if students have a mentorshipOption -->
                    {#if student.mentorshipOption}
                        <div class="row">
                            <div class="columns">
                                <div class="column is-1">
                                    <span class="is-small" on:click={updateMentorshipOption(student.id, student.mentorshipOption)}>
                                        <Icon data={pencil} />
                                    </span>
                                </div>
                                <!-- mentorshipOption title -->
                                <div class="column">
                                    {student.mentorshipOption}
                                </div>
                            </div>
                        </div>
                        {#if triggerId == student.id && triggerId != 0 && triggerContent === student.mentorshipOption && isAdmin === "true"}
                            <div class="row">
                                <UpdateCourseMentorship label={mentorShipOptionLabel} userId={student.id} />
                                <button class='button is-small' on:click={cancelEdit}>Cancel</button>
                            </div>
                        {/if}
                    <!-- if students DO NOT have a mentorshipOption -->
                    {:else}
                        <div class="row">
                            <div class="columns">
                                <div class="column is-1">
                                    <!-- spacer -->
                                </div>
                                <div class="column">
                                    None
                                </div>
                            </div>
                        </div>
                    {/if}
                </td>
                <!-- programStart -->
                <td>
                    <div class="row">
                        <div class="columns">
                            <div class="column is-1">
                                <span class="is-small" on:click={editStart(student.id, "Update startDate")}>
                                    <Icon data={pencil} />
                                </span>
                            </div>
                            <div class="column">
                                {student.programStart}
                            </div>
                        </div>
                    </div>
                    {#if triggerContent === "Update startDate" && triggerId === student.id && isAdmin === "true"}
                        <div class="columns">
                            <div class="column">
                                <UpdateDate studentId={student.id} label="Update startDate" />
                                <button class='button is-small' on:click={cancelEdit}>Cancel</button>
                            </div>
                        </div>
                    {/if}
                </td>
                <!-- programEnd -->
                <td>
                    <div class="row">
                        <div class="columns">
                            <div class="column is-1">
                                <span class="is-small" on:click={editEnd(student.id, "Update endDate")}>
                                    <Icon data={pencil} />
                                </span>
                            </div>
                            <div class="column">
                                {student.programEnd}
                            </div>
                        </div>
                    </div>
                    {#if triggerContent === "Update endDate" && triggerId === student.id && isAdmin === "true"}
                        <div class="columns">
                            <div class="column">
                                <UpdateDate studentId={student.id} label="Update endDate" />
                                <button class='button is-small' on:click={cancelEdit}>Cancel</button>
                            </div>
                        </div>
                    {/if}
                </td>
                <!-- assignedMentors -->
                <td>
                    {#if student.assignedMentors.length > 0}
                        {#each student.assignedMentors as mentor}
                            <a class="is-link is-small" href="/mentors/{mentor.id}">{mentor.firstName} {mentor.lastName[0]}. </a>
                            <br>
                        {/each}
                    {:else}
                        <div class="row">
                            <AssignMentor bind:student={student} />
                        </div>
                    {/if}
                </td>
            </tr>    
            {/each}
        {/if}
        </tbody>
    </table>
</div>
<footer class="footer">

</footer>