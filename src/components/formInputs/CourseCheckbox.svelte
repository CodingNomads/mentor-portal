<script>
    import { capitolize } from '../../js/capitolize.js';
    export let label;
    export let checked = [];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    
    <label><input type="checkbox" bind:group={checked} value="Python Programming">Python Programming</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Django Web Development">Django Web Development</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Flask Web Development">Flask Web Development</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="DSML">DataScience and Machine Learning</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Java Programming">Java Programming</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Spring Framework">Spring Framework</label>
</div>