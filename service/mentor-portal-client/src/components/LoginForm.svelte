<script>
    import InputText from './formInputs/InputText.svelte';
    import { lock, envelope } from 'svelte-awesome/icons';
    import { authorizedApiGetCall } from '../js/apiCalls';

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
        const response = await fetch(url, {
            headers,
            body,
            method: "POST",
            mode: "cors",
            credentials: "same-origin"
        })  
        if(response.status === 200){
            // store email and token
            const authToken = response.headers.get("Authorization")
            sessionStorage.setItem("authToken", authToken)
            sessionStorage.setItem("userEmail", email)
            // get request for userId required to redirect to detail page
            const getUrl = API_BASE_URL + `/api/user/${email}`
            const responseObject = await authorizedApiGetCall(authToken, getUrl)
            const userId = responseObject.id
            const isAdmin = responseObject.isAdmin
            sessionStorage.setItem("isAdmin", isAdmin)
            window.location.replace(CLIENT_BASE_URL + `/mentors/${userId}`)
        }
        else{
            alert("Invalid email or password. Please try again.")
        }
    };
        
</script>

<div class="container">
    <br>
    <br>
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <form class="form box" on:submit|preventDefault={submitLogin}>
                <h1 class="title">Mentor Portal Login</h1>
    
                <InputText label="email" icon={envelope} bind:value={email} type="email" placeholder="name@codingnomads.com" />
                <InputText label="password" icon={lock} bind:value={password} type="password" placeholder="*******" />
                
                <button class="button" type="submit">Sign In</button>
            </form>   
        </div>
    </div>
</div> 
