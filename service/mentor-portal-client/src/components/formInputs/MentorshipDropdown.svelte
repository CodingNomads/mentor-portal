<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    export let mentorshipOptions = [
        "Month-to-Month",
        "3-Month-Program",
        "Java Career",
        "Python Web Dev Career",
        "Python DSML Career"
    ];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select span="supportType" name={label} id={label} bind:value>
        {#each mentorshipOptions as option}
            <option class="dropdown-item" type="text" value={option}>{option}</option>
        {/each}
    </select>
</div>