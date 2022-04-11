<script>
    import Icon from 'svelte-awesome';
    import {capitolize} from '../../js/capitolize';


    export let label;
    export let value;
    export let icon;
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <div class="control has-icons-left">
        <input bind:value name={label} {...$$restProps} class="input" >
        <span class="icon is-small is-left">
            <Icon data={icon} />
        </span>
    </div>
</div>