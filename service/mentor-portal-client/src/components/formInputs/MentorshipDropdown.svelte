<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    export let mentorshipOptions = [
        "-- select mentorshipOption --",
        "None",
        "Month-to-Month",
        "3-Month-Program",
        "Java Career",
        "Python Web Dev Career",
        "Python DSML Career"
    ];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select span="mentorshipType" placeholder="mentorshipOptions" name={label} id={label} bind:value required>
        {#each mentorshipOptions as option (mentorshipOptions.indexOf(option))}
            <option id={option} class="dropdown-item" type="text" value={option}>{option}</option>
        {/each}
    </select>
</div>