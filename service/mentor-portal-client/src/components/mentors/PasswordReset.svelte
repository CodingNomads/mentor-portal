<script>
    import InputText from "../formInputs/InputText.svelte";

    let newPassword;
    let confirmNewPassword;

    async function resetPassword() {

    };
</script>

<form on:submit|preventDefault={resetPassword}>
    <div class="columns">
        <div class="column is-4">
            <InputText label="New Password" type="password" placeholder="*******" bind:value={newPassword} />
            <InputText label="Confirm New Password" type="password" placeholder="*******" bind:value={confirmNewPassword} />
            <br>
            <button class="button is-small">Submit</button>
        </div>
    </div>
</form>