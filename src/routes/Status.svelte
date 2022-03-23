<script>
    import NavBar from "../components/NavBar.svelte";
    import { onMount } from "svelte";
    export let api_status = "";

    onMount(async () => {
        const response = await fetch("http://localhost:8080/status", {
            mode: 'cors',
            credentials: "same-origin"
        })
        api_status = await response.json()
        console.log(api_status)
    })
</script>

<main>  
    <div class="container">
        <NavBar />
        <div class="columns is-left">
            <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
                <div class="card-body">
                    <h1 class="card-title"><strong>API STATUS</strong></h1>
                    <p>{api_status.status}</p>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</main>