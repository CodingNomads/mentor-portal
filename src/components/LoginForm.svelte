<script>
    let email = "";
    let password = "";
    let errors = {};
    let isLoading = false;
    let isSuccess = false;

    export let submit;

    const handleSubmit = () => {
        errors = {};
        if (email.length === 0) {
            errors.email = "Please enter an email";
        }
        if (password.length === 0) {
            errors.password = "Please enter your password";
        }
        if (Object.keys(errors).length === 0) {
            isLoading = true;
            submit ({email, password})
                .then(() => {
                    isSuccess = true;
                    isLoading - false;
                })
                .catch(err => {
                    errors.server = err;
                    isLoading = false;
                });
        }
        
    };   
</script>

<div class="container justify-content: center">
    <div class="row">
        <div class="container section scrollspy">
            <div class="col-6 col-md-4">
                logo in here
            </div>
            <div class="col-6 col-md-4 offset">
                <br>
                <form class="form" on:submit|preventDefault={handleSubmit}>
                    {#if isSuccess}
                    <div class="success">
                        You're logged in!
                    </div>
                    {:else}
                    <h1>Mentor Portal Login</h1>
                
                    <label class="label" for="email">Email</label>
                    <input class="input" type="text" name="email" placeholder="name@email.com" bind:value={email} />
                
                    <label class="label" for="password">Password</label>
                    <input class="input" type="password" name="password" placeholder="********" bind:value={password}/>
                    {/if}
                </form>
                <br>
                <button class="button" type="submit">Login</button>
                        {#if Object.keys(errors).length > 1}
                        <ul>
                            {#each Object.keys(errors) as field}
                            <li>{field}: {errors[field]}</li>
                            {/each}
                        </ul>
                        {/if}
            <!-- end column -->
            </div>            
        <!-- end container section -->
        </div>
    <!-- end row -->
    </div>  
<!-- end container -->
</div> 
