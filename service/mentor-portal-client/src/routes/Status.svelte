<script>
    import NavBar from "../components/NavBar.svelte";
    import { onMount } from "svelte";
    export let apiStatus = "";
    // import { env } from 'process';

    onMount(async () => {
        const url = API_BASE_URL + "/status"
        console.log(url)
        const response = await fetch(url, {
            mode: 'cors',
            credentials: "same-origin"
        })
        apiStatus = await response.json()
        console.log(apiStatus)
    })
</script>

<main>  
    <div class="container">
        <NavBar />
        <div class="columns is-left">
            <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
                <div class="card-body">
                    <h1 class="card-title"><strong>API STATUS</strong></h1>
                    <p>{apiStatus.status}</p>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</main>