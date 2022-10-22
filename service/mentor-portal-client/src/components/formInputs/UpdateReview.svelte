<script>
    import { authorizedApiPutCall } from "../../js/apiCalls.js"
    import StudentReviewDropdown from "./BooleanDropdown.svelte";

    export let studentId;
    const authToken = sessionStorage.getItem("authToken")
    let updatedReview;

    async function updateReview() {
        const url = API_BASE_URL + `/api/students/${studentId}`
        const body = JSON.stringify({
            "userId": studentId,
            "review": updatedReview
        })

        const response = await authorizedApiPutCall(authToken, body, url)

        if (response.status === 200) {
            alert("Successfully updated student review.")
            window.location.reload()
        }
        else {
            alert("Failed to update student review.")
        }
    }
</script>

<form class="form" on:submit|preventDefault={updateReview}>
    <StudentReviewDropdown label="Update Review" bind:value={updatedReview} />
    <button class="button is-small">Submit</button>
</form>