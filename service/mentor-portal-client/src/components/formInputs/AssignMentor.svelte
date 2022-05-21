<script>
    import { authorizedApiPostCall } from "../../js/apiCalls";

    import MentorDropdown from "./MentorDropdown.svelte";
    import MentorshipDropdown from "./MentorshipDropdown.svelte";
    
    export let mentor;
    export let student;
    let mentorshipOption;

    const authToken = localStorage.getItem("authToken")

    async function assign(){
        const url = API_BASE_URL + "/api/assignMentor"
        const body = JSON.stringify({
            "mentorId": mentor.id,
            "studentId": student.id,
            "mentorshipOption": mentorshipOption
        })
        const responseObject = await authorizedApiPostCall(authToken, body, url)
        console.log(responseObject)
        if (responseObject) {
            alert(`Successfully assigned mentor to Student.`)
            window.location.reload()
        } else {
            alert(`Failed to assign Mentor to Student`)
        }
    };
</script>

<form class="form" on:submit|preventDefault={assign}>
    <div class="columns">
        <div class="column">
            <div class="row">
                <MentorDropdown label="Assign: " bind:value={mentor} />
            </div>
            <div class="row">
                <MentorshipDropdown label="Mentorship Option" bind:value={mentorshipOption} />
            </div>
            <br>
            <div class="row">
                <button class="button is-small" type="submit" disabled={!mentor || !mentorshipOption}>Submit</button>
            </div>
        </div>
    </div>
</form>
