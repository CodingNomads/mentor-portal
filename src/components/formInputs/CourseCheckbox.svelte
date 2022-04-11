<script>
    import { capitolize } from '../../js/capitolize.js';
    export let label;
    export let checked = [];
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    
    <label><input type="checkbox" bind:group={checked} value="Python Programming"> Python Programming</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Django"> Django</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Flask"> Flask</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="DSML"> DSML</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Deep Learning"> Deep Learning</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Java Programming"> Java Programming</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Advanced Java and Spring"> Advanced Java and Spring</label>
    <br>
    <label><input type="checkbox" bind:group={checked} value="Javascript"> Javascript</label>
</div>