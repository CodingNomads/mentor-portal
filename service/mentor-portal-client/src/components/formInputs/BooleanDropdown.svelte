<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    const statusBooleans = [
        true,
        false
    ];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <div class="select is-small">
        <select name="updatedReview" id="updatedReview" bind:value>
            {#each statusBooleans as status (statusBooleans.indexOf(status))}
                <option value={status}>{status}</option>
            {/each}
        </select>
    </div>
</div>