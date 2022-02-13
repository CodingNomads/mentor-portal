<script>
    import InputText from './InputText.svelte';

    // user info
    let id = "";
    let firstName = "";
    let lastName = "";
    let roleCode = "";
    let statusCode = "";
    let timezoneOffset = "";
    // contact info
    let userId = "";
    let email = "";
    let telephone = "";
    let forumUsername = "";
    let slackUsername = "";
    // security info
    let username = "";
    let passwordHash = "";
    let isAdmin = "";

    const submitUser = () => console.log("User data submitted.")
    const submitContact = () => console.log("Contact data submitted.")
    const submitSecurity = () => console.log("Security data submitted.")
</script>

<div class="container">
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- user table form -->
            <form class="form box" on:submit|preventDefault={submitUser}>
                <h1>User Information</h1>

                <InputText label="id" bind:value={id}/>
                <InputText label="first name" bind:value={firstName}/>
                <InputText label="last name" bind:value={lastName}/>
                <InputText label="role code" bind:value={roleCode}/>
                <InputText label="status code" bind:value={statusCode}/>
                <InputText label="timezone offset" bind:value={timezoneOffset}/>
                
                <button class="button" type="submit">Submit</button>
            </form>  
            <!-- contact table form -->
            <form class="form box" on:submit|preventDefault={submitContact}>
                <h1>Contact Information</h1>

                <button class="button" type="Submit">Submit</button>
            </form> 
            <!-- security table form -->
            <form class="form box" on:submit|preventDefault={submitSecurity}>
                <h1>Security Information</h1>

                <button class="button" type="Submit">Submit</button>
            </form>

        </div>
    </div>
</div> 