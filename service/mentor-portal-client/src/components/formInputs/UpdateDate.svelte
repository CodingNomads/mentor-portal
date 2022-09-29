<script>
    import { authorizedApiPutCall } from "../../js/apiCalls";
    import DateSelect from "./DateSelect.svelte";

    export let studentId;
    export let label;
    let updatedDate;
    const authToken = sessionStorage.getItem("authToken")

    async function submitUpdate() {
        const url = API_BASE_URL + `/api/students/${studentId}`

        if (label === "Update startDate") {
            const body = JSON.stringify({
                "userId": studentId,
                "programStart": updatedDate
            })

            const response = await authorizedApiPutCall(authToken, body, url)

            if (response.status === 200) {
                alert(`Successfully updated programStart date.`)
                window.location.reload()
            }
            else {
                alert(`Failed to update programStart`)
            }
        }

        if (label === "Update endDate") {
            const body = JSON.stringify({
                "userId": studentId,
                "programEnd": updatedDate
            })
            const response = await authorizedApiPutCall(authToken, body, url)

            if (response.status === 200) {
                alert(`Successfully updated programEnd date.`)
                window.location.reload()
            }
            else {
                alert(`Failed to update programStart`)
            }
        }
    }
</script>

<form class="form" on:submit|preventDefault={submitUpdate}>
    <DateSelect label={label} bind:value={updatedDate} />
    <button class="button is-small">Submit</button>
</form>