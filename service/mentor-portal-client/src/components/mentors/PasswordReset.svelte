<script>
    import InputText from "../formInputs/InputText.svelte";

    import { authorizedApiPutCall } from "../../js/apiCalls.js";

    let email;
    let currentPassword;
    let newPassword;
    let confirmNewPassword;
    const authToken = localStorage.getItem("authToken")
    const userEmail = localStorage.getItem("userEmail")

    async function resetPassword() {
        if(userEmail === email && newPassword === confirmNewPassword){
            const url = API_BASE_URL + "/api/reset-password"
            const body = JSON.stringify({
                "email": email,
                "currentPassword": currentPassword,
                "newPassword": newPassword
            })
            const response = await authorizedApiPutCall(authToken, body, url)
            if(response.status === 200){
                alert("Succesfully updated your password.")
            } else {
                alert("Failed to update your password")
            }
        } else {
            alert("You may have miss-entered your email or password. Try again.")
        }
    };
</script>

<form on:submit|preventDefault={resetPassword}>
    <div class="columns">
        <div class="column is-4">
            <InputText label ="Email" type="email" placeholder="youremail@domain.com" bind:value={email} />
            <InputText label="Current Password" type="password" placeholder="*******" bind:value={currentPassword} />
            <InputText label="New Password" type="password" placeholder="*******" bind:value={newPassword} />
            <InputText label="Confirm New Password" type="password" placeholder="*******" bind:value={confirmNewPassword} />
            <br>
            <button class="button is-small">Submit</button>
        </div>
    </div>
</form>