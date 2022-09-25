<script>
    import { authorizedApiPutCall } from "../../js/apiCalls";
    import UserStatusDropdown from "./UserStatusDropdown.svelte";

    export let userId;
    const authToken = sessionStorage.getItem('authToken')
    let updatedStatus;

    async function submitUpdate () {
        const url = API_BASE_URL + `/api/students/${userId}`
        const body = JSON.stringify({
            'userId': userId,
            'statusDescription': updatedStatus
        })

        const response = await authorizedApiPutCall(authToken, body, url)

        if (response.status === 200) {
            alert(`Successfully updated student status.`)
            window.location.reload()
        }
        else {
            alert(`Failed to update student status.`)
        }
    }

</script>

<form class="form" on:submit|preventDefault={submitUpdate}>
    <UserStatusDropdown label="Update status" bind:value={updatedStatus}/>
    <button class="button is-small">Submit</button>
</form>