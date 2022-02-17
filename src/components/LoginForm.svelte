<script>
    import InputText from './formInputs/InputText.svelte';
    import {lock, envelope} from 'svelte-awesome/icons'

    let email = "";
    let password = "";

    const submitLogin = () => console.log("User is logged in")
        
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
