<script>
    import InputText from './formInputs/InputText.svelte';
    import { lock, envelope } from 'svelte-awesome/icons'
    import { user, isAuthenticated } from '../js/store.js';

    let email;
    let password;

    async function submitLogin(){
        const url = API_BASE_URL + "/login"
        const headers = {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "username": email,
            "password": password
        })
        let response = await fetch(url, {
            headers,
            body,
            method: "POST",
            mode: "cors",
            credentials: "same-origin"
        })  
        if(response.ok){
            // redirect to user detail page
            // const authToken = postResponse.headers.get("Authorization")
            // const responseHeaders = postResponse.headers
            console.log(response)
            let authToken = response.headers.get("Authorization")
            console.log(authToken)
            // console.log(`AuthToken: ${authToken}`)         
        }
        else if(response.error){
            alert("Invalid email or password. Please try again.")
        }
    };
        
</script>

<div class="container">
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <form class="form box" on:submit|preventDefault={submitLogin}>
                <h1>Mentor Portal Login</h1>
    
                <InputText label="email" icon={envelope} bind:value={email} type="email" placeholder="name@codingnomads.com" />
                <InputText label="password" icon={lock} bind:value={password} type="password" placeholder="*******" />
                
                <button class="button" type="submit">Sign In</button>
            </form>   
        </div>
    </div>
</div> 
