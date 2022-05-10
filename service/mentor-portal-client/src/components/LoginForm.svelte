<script>
    import InputText from './formInputs/InputText.svelte';
    import { lock, envelope } from 'svelte-awesome/icons';
    import { userAuth } from '../js/store.js';

    let email;
    let password;
    let userWallet;

    userAuth.subscribe(value => userWallet = value)

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
        if(response.ok){
            // redirect to user detail page
            const authToken = response.headers.get("Authorization")
            const userId = response.headers.get("userId")
            userWallet =[{"email": `${email}`, "authToken": `${authToken}`}]
            localStorage.setItem("authToken", authToken)
            console.log(userId)
            window.location.replace(BASE_CLIENT_URL + `/mentors/${userId}`)
        }
        else if(!response.ok){
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
