<script>
    import { plusCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import CreateSupportLog from "../CreateSupportLog.svelte";

    // initialize variables
    export let student;
    let addLog = false;

    // handler functions
    const newLog = () => {
        return addLog = true;
    };
    const resetAddLog = () => {
        return addLog = false;
    };
    const submitLog = () => {
        
    };
</script>

<div class="container">
    <!-- add new log button -->
    <div class="row">
        <button class="button is-medium is-focused" on:click={newLog}>
            <Icon data={plusCircle} />
        </button>
    </div>

    <br>
    {#if addLog === true}
        <!-- support log form displays on click -->
        <CreateSupportLog />
        <div class="row">
            <button class="button is-danger" on:click={resetAddLog}>Cancel</button>
        </div>
        <br>
    {/if}
    
    <!-- display past logs -->
    {#each student.supportLog as entry (entry.id)}
        <div class="box">
            <div class="row">
                <div class="columns">
                    <!-- meta data -->
                    <div class="column is-one-fifth">
                        <p><em>{entry.createdAt}</em></p>
                        <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                        <p><em>{entry.duration}</em></p>
                    </div>
                    <!-- support log topic body -->
                    <div class="column is-four-fifths">
                        <p>{entry.log}</p>
                    </div>
                </div>
            </div>
        </div>
    {/each}    
</div>
