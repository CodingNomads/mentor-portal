<script>
    import { capitolize } from "../../js/capitolize.js";
    export let label;
    export let value;
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <input type="date" bind:value>
</div>