<script>
    import MentorshipDropdown from "./MentorshipDropdown.svelte";
    import CourseDropdown from "./CourseDropdown.svelte";

    export let label;
    export let userId;
    export let value;

    async function submitUpdate() {
        const url = API_BASE_URL + `/api/students/${userId}`
        const headers = {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        if(label === "updatedCourseTrack"){
            const body = JSON.stringify({
                "userId": userId,
                "courseTrack": value,
            })
            console.log(body)
            const response = await fetch(url, {
                headers,
                body,
                method: "PUT",
                mode: "cors",
                credentials: "same-origin"
            })
            const putResponse = JSON.stringify(response.json())
            if(response.ok){
                alert("Successfully updated student courseTrack.")
                window.location.reload()
            }else{
                alert("Failed to update student courseTrack")
            }
        };
        if(label === "updatedMentorshipOption"){
            const body = JSON.stringify({
                "userId": userId,
                "mentorshipOption": value,
            })
            const response = await fetch(url, {
                headers,
                body,
                method: "PUT",
                mode: "cors",
                credentials: "same-origin"
            })
            const putResponse = JSON.stringify(response.json())
            if(response.ok){
                alert("Successfully updated student mentorshipOption.")
                window.location.reload()
            }else{
                alert("Failed to update student mentorshipOption")
            }
        };
    };
</script>

{#if label === "updatedCourseTrack"}
    <form class="form" on:submit|preventDefault={submitUpdate}>
        <CourseDropdown label={label} bind:value={value} />
    <button class="button is-small">Submit</button>
</form>
{/if}

{#if label === "updatedMentorshipOption"}
    <form class="form" on:submit|preventDefault={submitUpdate}>
        <MentorshipDropdown label={label} bind:value={value} />
        <button class="button is-small">Submit</button>
    </form>
{/if}