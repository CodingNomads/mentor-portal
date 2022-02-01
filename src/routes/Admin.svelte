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
    <div class="tabs is-left">
        <ul class="nav nav-tabs">
        {#each navOptions as option, i}
            <a class={intSelected==i ? "nav-link active p-2 ml-1" : "p-2 ml-1 nav-link"} on:click={changeComponent} id={i} role="tab">{option.page}</a>
        {/each}
        </ul>
    </div>
    <!-- display selected navigation -->
    <svelte:component this={selected.component} />        
</div>