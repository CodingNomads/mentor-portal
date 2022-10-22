<script>
    import { onDestroy, onMount } from "svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls.js";
    import Icon from 'svelte-awesome';
    import { pencil } from "svelte-awesome/icons";
    import UpdateDate from "../formInputs/UpdateDate.svelte";
    import UpdateReview from "../formInputs/UpdateReview.svelte";
    import StudentInfo from "./StudentInfo.svelte";
    import UpdateLinkedinAlumni from "../formInputs/UpdateLinkedinAlumni.svelte";
    import UpdateCourseMentorship from "../formInputs/UpdateCourseMentorship.svelte";

    export let student;
    let mentorStudentLookupData;
    let authToken = sessionStorage.getItem("authToken")
    const isAdmin = sessionStorage.getItem("isAdmin")

    // edit variables
    let label;
    let triggerId;

    function editCourseTrack(studentId, labelTitle) {
        triggerId = studentId;
        label = labelTitle;
        return triggerId && label
    }

    function editDate(studentId, labelTitle) {
        triggerId = studentId;
        label = labelTitle;
        return triggerId && label
    }

    function cancelEdit () {
        triggerId = 0;
        label = "";
        return triggerId & label
    }

    function editReview(studentId, labelTitle) {
        triggerId = studentId;
        label = labelTitle;
        return triggerId & label
    }

    function editLinkedInAlumni(studentId, labelTitle) {
        triggerId = studentId;
        label = labelTitle;
        return triggerId & label
    }

    onMount(async () => {
        const url = API_BASE_URL + `/api/msl/${student.id}`
        mentorStudentLookupData = await authorizedApiGetCall(authToken, url)
    })
</script>

<table class="table is-fullwidth is-bordered">
    <thead>
        <tr>
            <th>Course Track</th>
            <th>Program Start</th>
            <th>Program End</th>
            <th>Student Review</th>
            <th>LinkedIn Alumni</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <!-- courseTrack -->
            <td>
                <div class="row">
                    <div class="columns">
                        <div class="column is-1">
                            <span class="is-small" on:click={editCourseTrack(student.id, "Update Course")}>
                                <Icon data={pencil} />
                            </span>
                        </div>
                        <div class="column">
                            {student.courseTrack}
                        </div>
                    </div>
                </div>
                {#if label === "Update Course" && triggerId === student.id && isAdmin === "true"}
                    <div class="columns">
                        <div class="column">
                            <UpdateCourseMentorship label="Update Course" userId={student.id} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        </div>
                    </div>
                {/if}
            </td>
            <!-- programStart -->
            <td>
                <div class="row">
                    <div class="columns">
                        <div class="column is-1">
                            <span class="is-small" on:click={editDate(student.id, "updateStart")}>
                                <Icon data={pencil} />
                            </span>
                        </div>
                        <div class="column">
                            {student.programStart}
                        </div>
                    </div>
                </div>
                {#if label === "updateStart" && triggerId === student.id && isAdmin === "true"}
                    <!-- <div class="row"> -->
                        <div class="columns">
                            <div class="column">
                                <UpdateDate label="Update startDate" studentId={student.id} />
                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                            </div>
                        </div>
                    <!-- </div> -->
                {/if}
            </td>
            <!-- programEnd -->
            <td>
                <div class="row">
                    <div class="columns">
                        <div class="column is-1">
                            <span class="is-small" on:click={editDate(student.id, "updateEnd")}>
                                <Icon data={pencil} />
                            </span>
                        </div>
                        <div class="column">
                            {student.programEnd}
                        </div>
                    </div>
                </div>
                {#if label === "updateEnd" && triggerId === student.id && isAdmin === "true"}
                    <div class="columns">
                        <div class="column">
                            <UpdateDate label="Update endDate" studentId={student.id} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        </div>
                    </div>
                {/if}
            </td>
            <!-- studentReview -->
            <td>
                <div class="row">
                    <div class="columns">
                        <div class="column is-1">
                            <span class="is-small" on:click={editReview(student.id, "updateReview")}>
                                <Icon data={pencil} />
                            </span>
                        </div>
                        <div class="column">
                            {student.review}
                        </div>
                    </div>
                </div>
                {#if label === "updateReview" && triggerId === student.id && isAdmin === "true" }
                    <div class="columns">
                        <div class="column">
                            <UpdateReview label="Update Review" studentId={student.id} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        </div>
                    </div>
                {/if}
            </td>
            <!-- linkedinAlumni -->
            <td>
                <div class="row">
                    <div class="columns">
                        <div class="column is-1">
                            <span class="is-small" on:click={editLinkedInAlumni(student.id, "updateLinkedinAlumni")}>
                                <Icon data={pencil} />
                            </span>
                        </div>
                        <div class="column">
                            {student.linkedinAlumni}
                        </div>
                    </div>
                </div>
                {#if label === "updateLinkedinAlumni" && triggerId === student.id && isAdmin === "true"}
                    <div class="columns">
                        <div class="column">
                            <UpdateLinkedinAlumni studentId={student.id} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        </div>
                    </div>
                {/if}
            </td>
        </tr>
    </tbody>
</table>

{#if student.assignedMentors}
<table class="table is-bordered">
    <thead>
        <tr>
            <th>Mentor Name</th>
        </tr>
    </thead>
    <tbody>
        {#each student.assignedMentors as mentor}
            <tr>
                <!-- mentorName -->
                <td>{mentor.firstName} {mentor.lastName}</td>
            </tr>
        {/each}
    </tbody>
</table>
{:else}
<div>
    <p>This student currently doesn't have a mentor</p>
</div>
{/if}