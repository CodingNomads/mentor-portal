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
        <CreateSupportLog studentId={student.id}/>
        <div class="row">
            <button class="button is-danger" on:click={resetAddLog}>Cancel</button>
        </div>
        <br>
    {/if}
    
    <!-- display past logs -->
    {#if student.supportLog.length > 0}
        {#each student.supportLog.reverse() as entry (entry.id)}
            <div class="box">
                <div class="row">
                    <div class="columns">
                        <!-- meta data -->
                        <div class="column is-one-fifth">
                            <p><em>{entry.logDate}</em></p>
                            <p><em>{entry.type}</em></p>
                            <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                            <p><em>{entry.duration} minutes</em></p>
                        </div>
                        <!-- support log topic body -->
                        <div class="is-offset-1 column is-three-fifths">
                            <p>{entry.log}</p>
                        </div>
                    </div>
                </div>
            </div>
        {/each}
    {/if}    
</div>
