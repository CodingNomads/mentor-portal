<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    export let supportTypeList = [
        "Scheduled 1:1",
        "Impromptu 1:1",
        "Slack/Discord",
        "Forum",
        "Email",
        "Other"
    ];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select span="supportType" name={label} id={label} bind:value>
        {#each supportTypeList as type}
            <option class="dropdown-item" type="text" value={type}>{type}</option>
        {/each}
    </select>
</div>
