<script>
    import { plusCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import CreateSupportLog from "../CreateSupportLog.svelte";
    import UpdateLog from "../formInputs/UpdateLog.svelte";

    // initialize variables
    export let mentor;
    const userEmail = sessionStorage.getItem("userEmail")
    const isAdmin = sessionStorage.getItem("isAdmin")
    let addLog = false;
    let editLogId;

    // count flagged logs
    let flaggedLogCount = mentor.supportLog.filter(log => log.flag === true).length 
    // filter logs by date
    let supportLogList = mentor.supportLog.sort(function(a, b){
        return new Date(b.logDate) - new Date(a.logDate)
    })

    // handler functions
    const newLog = () => {
        return addLog = true;
    };
    const resetAddLog = () => {
        return addLog = false;
    };
    function editLog(supportLogId) {
        editLogId = supportLogId
        return editLogId
    }
    const cancelEdit = () => {
        editLogId = 0;
        return editLogId
    }
</script>

<div class="container">
    <!-- add new log button -->
    <div class="row">
        {#if isAdmin === "true" || userEmail === mentor.email}
            <button class="button is-medium" on:click={newLog}>
                <Icon data={plusCircle} scale={3} />
            </button>
        {/if}
    </div>

    <br>
    {#if addLog === true}
        <!-- support log form displays on click -->
        <CreateSupportLog studentId={mentor.id} studentFlag={mentor.flag} flaggedLogCount={flaggedLogCount} />
        <div class="row">
            <button class="button is-small" on:click={resetAddLog}>Cancel</button>
        </div>
        <br>
    {/if}
    
    <!-- display past logs -->
    {#if mentor.supportLog.length > 0}
        <table class="table is-fullwidth">
            <tbody>
                {#each supportLogList as entry (entry.id)}
                    <!-- catch flagged logs -->
                    {#if entry.flag === true}
                        <tr>
                            <div class="notification is-danger is-light">
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
                                        {#if isAdmin === "true"}
                                            <br>
                                            <br>
                                            <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                        {/if}
                                    </div>
                                </div>
                                <!-- triggered edit form -->
                                {#if editLogId === entry.id}
                                    <UpdateLog log={entry.log} userId={mentor.id} supportLogId={entry.id} />
                                    <br>
                                    <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                                {/if}
                            </div>
                        </tr>
                    <!-- non-flagged logs -->
                    {:else}
                        <tr>
                            <div class="box">
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
                                        {#if isAdmin === "true"}
                                            <br>
                                            <br>
                                            <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                        {/if}
                                    </div>
                                </div>
                                <!-- triggered edit form -->
                                {#if editLogId === entry.id}
                                    <UpdateLog log={entry.log} userId={mentor.id} supportLogId={entry.id} />
                                    <br>
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
