<script>
    import { plusCircle } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import CreateSupportLog from "../CreateSupportLog.svelte";

    // initialize variables
    let student;
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
    export let supportLog = [
        {
            id: 1,
            date: "2022-02-14 (11:00)",
            type: "1:1 call",
            duration: "45 minutes",
            log: "discussed the high-level programming skills that employers look for: ability to problem-solve; the ability to transition between technologies and learn new ones."
        }
    ];
</script>

<div class="container">
    <!-- add new log button -->
    <div class="row">
        <button class="button is-large is-focused" on:click={newLog}>
            <span class="icon is-large">
                <Icon data={plusCircle} />
            </span>
        </button>
    </div>
    <br>
    {#if addLog === true}
        <!-- support log form displays on click -->
        <CreateSupportLog />
        <div class="row">
            <button class="button is-danger" on:click={resetAddLog}>Cancel</button>
        </div>
    {/if}
    <br>
    <!-- display past logs -->
    {#each supportLog as entry (entry.id)}
        <div class="box">
            <div class="row">
                <div class="columns">
                    <!-- meta data -->
                    <div class="column is-one-fifth">
                        <p><em>{entry.date}</em></p>
                        <p><em>{entry.type}</em></p>
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
