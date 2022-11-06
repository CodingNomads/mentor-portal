<script>
    import { plusCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import CreateSupportLog from "../CreateSupportLog.svelte";
    import UpdateLog from "../formInputs/UpdateLog.svelte";

    // admin check
    const isAdmin = sessionStorage.getItem("isAdmin")
    const mentorId = sessionStorage.getItem("mentorId")

    // initialize variables
    export let student;
    let addLog = false;
    let editTrigger = false;
    let editLogId;

    // count flagged logs
    let flaggedLogCount = student.supportLog.filter(log => log.flag === true).length
    // filter logs by date
    let supportLogList = student.supportLog.sort(function(a, b){
        return new Date(b.logDate) - new Date(a.logDate) 
    }) 

    // handler functions
    const newLog = () => {
        addLog = true;
        return addLog
    };
    const resetAddLog = () => {
        return addLog = false
    };
    function editLog(supportLogId) {
        editLogId = supportLogId;
        editTrigger = true;
        return editTrigger && editLogId
    };
    const cancelEdit = () => {
        editLogId = 0;
        editTrigger = false;
        return editTrigger && editLogId
    };
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
        <table class="table is-fullwidth">
            <tbody>
                {#each supportLogList as entry (entry.id)}
                    <!-- catch flagged logs -->
                    {#if entry.flag === true}
                        <tr>
                            <div class="notification is-danger">
                                {#if editTrigger === false}
                                <div class="columns">
                                    <!-- meta data -->
                                    <div class="column is-one-fifth">
                                        <p><em>{entry.logDate.split("T")[0]}</em></p>
                                        <p><em>{entry.type}</em></p>
                                        <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                                    </div>
                                    <!-- support log topic body -->
                                    <div class="is-offset-1 column is-three-fifths">
                                        <!-- {#if editTrigger === false} -->
                                            <div class="content">
                                                {@html entry.log}
                                            </div>
                                            {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                                <br>
                                                <br>
                                                <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                            {/if}
                                        <!-- {/if} -->
                                        <!-- triggered edit form -->
                                        <!-- {#if editTrigger === true}
                                            {#if editLogId === entry.id && isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                                <UpdateLog log={entry.log} userId={student.id} supportLogId={entry.id} />
                                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                                            {/if}
                                        {/if} -->
                                    </div>
                                </div>
                                {/if}
                                <!-- triggered edit form -->
                                {#if editTrigger === true && editLogId === entry.id}
                                    <UpdateLog log={entry.log} userId={student.id} supportLogId={entry.id} />
                                    <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                                {/if}
                            </div>
                        </tr>
                    <!-- non-flagged logs -->
                    {:else}
                        <tr>
                            <div class="box">
                                {#if editTrigger === false}
                                    <div class="columns">
                                        <!-- meta data -->
                                        <div class="column is-one-fifth">
                                            <p><em>{entry.logDate.split("T")[0]}</em></p>
                                            <p><em>{entry.type}</em></p>
                                            <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                                        </div>
                                        <!-- support log topic body -->
                                        <div class="is-offset-1 column is-three-fifths">
                                            <div class="content">
                                                {@html entry.log}
                                            </div>
                                            {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                                <br>
                                                <br>
                                                <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                            {/if}
                                        </div>
                                    </div>
                                {/if}
                                {#if editTrigger === true && editLogId === entry.id}
                                    <UpdateLog log={entry.log} userId={student.id} supportLogId={entry.id} />
                                    <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                                {/if}
                            </div>
                        </tr>
                    {/if}
                    <br>
                {/each}
            </tbody>
        </table>
    {/if}    
</div>
