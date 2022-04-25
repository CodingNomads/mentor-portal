<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    export let courseTrackList = [
        "Python Programming",
        "Django",
        "Flask",
        "DSML",
        "Deep Learning",
        "Java Programming",
        "Advanced Java and Spring",
        "Javascript"
    ]
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select span="supportType" name={label} id={label} bind:value>
        {#each courseTrackList as course}
            <option class="dropdown-item" type="text" value={course}>{course}</option>
        {/each}
    </select>
</div>