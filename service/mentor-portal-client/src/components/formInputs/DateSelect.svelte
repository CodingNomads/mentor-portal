<script>
    export let label;
    export let value;
</script>

<div class="field">
    <label for={label} class="label">{label}</label>
    <input type="date" bind:value>
</div>