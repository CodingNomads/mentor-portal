<script>
    import NavBar from "../components/NavBar.svelte";
    import { onMount } from "svelte";
    import { authorizedApiGetCall } from "../js/apiCalls";
    
    export let apiStatus;
    let authToken = localStorage.authToken

    onMount(async () => {
        const url = API_BASE_URL + "/status"
        const responseObject = await authorizedApiGetCall(authToken, url)
    
        if(responseObject){
            apiStatus = responseObject.status
            console.log(responseObject)
        } 
        // if invalid authentication
        else if(responseObject.status === 403){
            window.location.replace(CLIENT_BASE_URL + "/login")
        }
    })
</script>

<main>  
    <div class="container">
        <NavBar />
        <div class="columns is-left">
            <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
                <div class="card-body">
                    <h1 class="card-title"><strong>API STATUS</strong></h1>
                    {#if authToken && apiStatus === "Ok"}
                        <p>{apiStatus}</p>
                    {:else}
                        <p class="card-text">Viewing this may require permissions you do not have. Either that or something went wrong. Please talk to Scott or Jon.</p>
                    {/if}
                    <hr>
                </div>
            </div>
        </div>
    </div>
</main>