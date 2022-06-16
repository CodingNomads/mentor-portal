<script>
    import { capitolize } from "../../js/capitolize";

    export let label;
    export let value;
    export let courseTrackList = [
        "None",
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
    <select span="courseType" name={label} id={label} bind:value required>
        {#each courseTrackList as course (courseTrackList.indexOf(course))}
            <option class="dropdown-item" type="text" value={course}>{course}</option>
        {/each}
    </select>
</div>