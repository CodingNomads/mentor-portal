<script>
    import { plusCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import CreateSupportLog from "../CreateSupportLog.svelte";
    import UpdateLog from "../formInputs/UpdateLog.svelte";
    import { onDestroy } from "svelte";

    // admin check
    const isAdmin = sessionStorage.getItem("isAdmin")
    const mentorId = sessionStorage.getItem("mentorId")

    // initialize variables
    export let student;
    let addLog = false;
    let editTrigger = false;

    // count flagged logs
    let flaggedLogCount = student.supportLog.filter(log => log.flag === true).length
    // filter logs by date
    let supportLogList = student.supportLog.sort(function(a, b){
        return new Date(b.logDate) - new Date(a.logDate) 
    }) 

    // handler functions
    const newLog = () => {
        return addLog = true;
    };
    const resetAddLog = () => {
        return addLog = false;
    };
    const editLog = () => {
        return editTrigger = true
    }
    const cancelEdit = () => {
        return editTrigger = false
    }

    onDestroy(editTrigger, addLog)
</script>

<div class="container">
    <!-- add new log button -->
    <div class="row">
        <button class="button is-medium" on:click={newLog}>
            <Icon data={plusCircle} scale={3} />
        </button>
    </div>

    <br>
    {#if addLog === true}
        <!-- support log form displays on click -->
        <CreateSupportLog studentId={student.id} studentFlag={student.flag} flaggedLogCount={flaggedLogCount} />
        <div class="row">
            <button class="button is-small" on:click={resetAddLog}>Cancel</button>
        </div>
        <br>
    {/if}
    
    <!-- display past logs -->
    {#if student.supportLog.length > 0}
        {#each supportLogList as entry (entry.id)}
            <!-- catch flagged logs -->
            {#if entry.flag === true}
                <div class="notification is-danger">
                    <div class="row">
                        <div class="columns">
                            <!-- meta data -->
                            <div class="column is-one-fifth">
                                <p><em>{entry.logDate.split("T")[0]}</em></p>
                                <p><em>{entry.type}</em></p>
                                <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                            </div>
                            <!-- support log topic body -->
                            <div class="is-offset-1 column is-three-fifths">
                                {#if editTrigger === false}
                                <p>{entry.log}</p>
                                    {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                        <br>
                                        <br>
                                        <button class="button is-small" on:click={editLog}>Edit</button>
                                    {/if}
                                <!-- triggered edit form -->
                                {:else}
                                    <UpdateLog log={entry.log} userId={student.id} supportLogId={entry.id} />
                                    <br>
                                    <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                                {/if}
                            </div>
                        </div>
                    </div>
                </div>
            {:else}
            <div class="box">
                <div class="row">
                    <div class="columns">
                        <!-- meta data -->
                        <div class="column is-one-fifth">
                            <p><em>{entry.logDate.split("T")[0]}</em></p>
                            <p><em>{entry.type}</em></p>
                            <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                        </div>
                        <!-- support log topic body -->
                        <div class="is-offset-1 column is-three-fifths">
                            {#if editTrigger === false}
                                <p>{entry.log}</p>
                                {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                    <br>
                                    <br>
                                    <button class="button is-small" on:click={editLog}>Edit</button>
                                {/if}
                            <!-- triggered edit form -->
                            {:else}
                                <UpdateLog log={entry.log} userId={student.id} supportLogId={entry.id} />
                                <br>
                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                            {/if}
                        </div>
                    </div>
                </div>
            </div>
            {/if}
        {/each}
    {/if}    
</div>
