<script>
    import BooleanDropdown from "./BooleanDropdown.svelte";
    import { authorizedApiPutCall } from "../../js/apiCalls.js";

    export let studentId;
    let updatedBoolean;
    const authToken = sessionStorage.getItem("authToken");

    async function submitUpdate() {
        const url = API_BASE_URL + `/api/students/${studentId}`;
        const body = JSON.stringify({
            "userId": studentId,
            "linkedinAlumni": updatedBoolean
        });

        const response = await authorizedApiPutCall(authToken, body, url);

        if (response.status === 200) {
            alert("Successfully updated Linkedin Alumni.")
            window.location.reload()
        } else {
            alert("Failed to update Linkedin Alumni.")
        }
    }

</script>

<form class="form" on:submit|preventDefault={submitUpdate}>
    <BooleanDropdown label="Update Linkedin Alumni" bind:value={updatedBoolean} />
    <button class="button is-small">Submit</button>
</form>