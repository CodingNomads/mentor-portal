<script>
    import { authorizedApiPutCall } from "../../js/apiCalls";

    import MentorshipDropdown from "./MentorshipDropdown.svelte";
    import CourseDropdown from "./CourseDropdown.svelte";

    export let label;
    export let userId;
    export let value;

    const authToken = localStorage.getItem("authToken")

    async function submitUpdate() {
        const url = API_BASE_URL + `/api/students/${userId}`
        if(label === "Update Course"){
            const body = JSON.stringify({
                "userId": userId,
                "courseTrack": value,
            })
            const response = await authorizedApiPutCall(authToken, body, url)
            console.log(body)
            if(response.status === 200){
                alert("Successfully updated student courseTrack.")
                window.location.reload()
            }else{
                alert("Failed to update student courseTrack")
            }
        };
        if(label === "Update Mentorship"){
            const body = JSON.stringify({
                "userId": userId,
                "mentorshipOption": value,
            })
            const response = await authorizedApiPutCall(authToken, body, url)
            if(response.status === 200){
                alert("Successfully updated student mentorshipOption.")
                window.location.reload()
            }else{
                alert("Failed to update student mentorshipOption")
            }
        };
    };
</script>

{#if label === "Update Course"}
    <form class="form" on:submit|preventDefault={submitUpdate}>
        <CourseDropdown label={label} bind:value={value} />
    <button class="button is-small">Submit</button>
</form>
{/if}

{#if label === "Update Mentorship"}
    <form class="form" on:submit|preventDefault={submitUpdate}>
        <MentorshipDropdown label={label} bind:value={value} />
        <button class="button is-small">Submit</button>
    </form>
{/if}