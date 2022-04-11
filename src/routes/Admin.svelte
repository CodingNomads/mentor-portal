<script>
    import NavBar from '../components/NavBar.svelte';
    import { navOptions } from '../components/CreateNav.svelte';
    let selected = navOptions[0];
    let intSelected = 0;

    function changeComponent(event) {
        selected = navOptions[event.srcElement.id];
        intSelected = event.srcElement.id;
    }
</script>

<div class="container">
    <NavBar />
    <!-- create navigation -->
    <br>
    <div class="row">
        {#each navOptions as option, i}
            <a class={intSelected==i ? "button is-small is-info active p-2 ml-1" : "p-2 ml-1 button is-light is-small"} on:click={changeComponent} id={i} role="tab">{option.page}</a>
        {/each}
    </div>
    <!-- display selected navigation -->
    <svelte:component this={selected.component} />        
</div>