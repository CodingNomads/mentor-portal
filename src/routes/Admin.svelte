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

<NavBar />
<div class="container">
    <!-- create navigation -->
    <br>
    <ul class="nav nav-tabs">
        {#each navOptions as option, i}
            <button class={intSelected==i ? "nav-link active p-2 ml-1" : "p-2 ml-1 nav-link"} on:click={changeComponent} id={i} role="tab">{option.page}</button>
        {/each}
    </ul>
    <!-- display selected navigation -->
    <div class="row">	
        <svelte:component this={selected.component} />
    </div>        
</div>