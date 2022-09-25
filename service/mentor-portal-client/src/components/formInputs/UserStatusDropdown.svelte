<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    
    let statusOptions = [
        "-- select statusDescription --",
        "Active",
        "Paused",
        "Cancelled",
        "Completed program",
        "Completed curriculum",
        "Dropped out",
        "MIA",
    ];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <div class="select is-small">
        <select name="statusDescription" id="statusDescription" bind:value required>
            {#each statusOptions as status (statusOptions.indexOf(status))}
                <option value={status}>{status}</option>
            {/each}
        </select>
    </div>
</div>