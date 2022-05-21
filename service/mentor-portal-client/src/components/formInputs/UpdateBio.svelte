<script>
    import { authorizedApiPutCall } from "../../js/apiCalls.js";

    export let bio;
    export let mentorId;
    export let studentId;
    export let updatedBio;

    const authToken = localStorage.getItem("authToken")

    let mentorUrl = API_BASE_URL + `/api/mentors/${mentorId}`;
    let studentUrl = API_BASE_URL + `/api/students/${studentId}`;

    async function submitUpdate() {
        // if student being updated
        if (typeof(studentId) === 'number' && typeof(mentorId) === 'undefined') {
            const body = JSON.stringify({
                'userId': studentId,
                'bio': updatedBio
            })
            const response = await authorizedApiPutCall(authToken, body, studentUrl)
            if (response.status === 200) {
                alert(`Successfully updated bio.`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
        // if mentor being updated
        if (typeof(mentorId) === 'number' && typeof(studentId) === 'undefined') {
            const body = JSON.stringify({
                'userId': mentorId,
                'bio': updatedBio
            })
            const response = await authorizedApiPutCall(authToken, body, mentorUrl)
            if (response.status === 200) {
                alert(`Successfully updated bio`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
    }

</script>

<div class="box">
    {bio}
</div>
<form class="form" on:submit|preventDefault={submitUpdate}>
    <textarea  class="textarea" bind:value={updatedBio} cols="100" rows="5"></textarea>
    <br>
    <button class="button is-small">Submit</button>
</form>