<script>
    import { capitolize } from "../../js/capitolize";
    import Icon from 'svelte-awesome';
    import { arrowDown } from "svelte-awesome/icons";

    let label;
    let userId;
    let value;
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
    <div class="select is-small">
        <select name="statusDescription" id="statusDescription" bind:value required>
            {#each statusOptions as status (statusOptions.indexOf(status))}
                <option value={status}>{status}</option>
            {/each}
        </select>
    </div>
</div>