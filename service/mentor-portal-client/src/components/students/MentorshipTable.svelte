<script>
    import { onDestroy, onMount } from "svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls.js";
    import Icon from 'svelte-awesome';
    import { pencil } from "svelte-awesome/icons";
    import UpdateDate from "../formInputs/UpdateDate.svelte";

    export let student;
    let mentorStudentLookupData;
    let authToken = sessionStorage.getItem("authToken")
    const isAdmin = sessionStorage.getItem("isAdmin")

    // edit variables
    let label;
    let triggerId;

    function editDate(studentId, labelTitle) {
        triggerId = studentId;
        label = labelTitle
        return triggerId && label
    }

    function cancelEdit () {
        triggerId = 0;
        label = "";
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
            <th>courseTrack</th>
            <th>programStart</th>
            <th>programEnd</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <!-- courseTrack -->
            <td>
                {student.courseTrack}
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
                    <!-- <div class="row"> -->
                        <div class="columns">
                            <div class="column">
                                <UpdateDate label="Update endDate" studentId={student.id} />
                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                            </div>
                        </div>
                    <!-- </div> -->
                {/if}
            </td>
        </tr>
    </tbody>
</table>

{#if student.assignedMentors}
<table class="table is-bordered">
    <thead>
        <tr>
            <th>mentorName</th>
            <!-- <th>mentorshipStart</th>
            <th>mentorshipEnd</th>
            <th>mentorshipStatus</th> -->
        </tr>
    </thead>
    <tbody>
        {#each student.assignedMentors as mentor}
            <tr>
                <!-- mentorName -->
                <td>{mentor.firstName} {mentor.lastName}</td>
                <!-- startDate -->
                <!-- <td></td> -->
                <!-- endDate -->
                <!-- <td></td> -->
                <!-- mentorshipStatus -->
                <!-- <td></td> -->
            </tr>
        {/each}
    </tbody>
</table>
{:else}
<div>
    <p>This student currently doesn't have a mentor</p>
</div>
{/if}