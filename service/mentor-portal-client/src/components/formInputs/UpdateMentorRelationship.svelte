<script>
    import { authorizedApiPutCall } from "../../js/apiCalls.js";
    import UserStatusDropdown from "./UserStatusDropdown.svelte";

    const authToken = sessionStorage.getItem("authToken");
    export let relationId;
    export let mentorId;
    export let studentId;
    let description;

    async function submitUpdate() {
        const url = API_BASE_URL + `/api/msl/${studentId}`
        const body = JSON.stringify({
            "id": relationId,
            "mentorId": mentorId,
            "studentId": studentId,
            "description": description
        })
        const response = await authorizedApiPutCall(authToken, body, url)

        if (response.status === 200){
            alert("Successfully updated mentorship status.")
            // window.location.reload()
        }
        else {
            alert("Failed to update mentorship status.")
        }
      
    }
</script>

<form class="form" on:submit|preventDefault={submitUpdate}>
    <UserStatusDropdown label="Update Mentorship Status" bind:value={description} />
    <button class="button is-small">Submit</button>
</form>