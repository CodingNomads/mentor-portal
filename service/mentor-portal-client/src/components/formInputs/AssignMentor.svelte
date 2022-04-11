<script>
    import StudentList from "../students/StudentList.svelte";

    import MentorDropdown from "./MentorDropdown.svelte";
    
    export let mentor;
    export let student;

    async function assign(){
        // let mentorId = mentor.id
        // let studentId = student.id
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "mentorId": mentor.id,
            "studentId": student.id
        })
        const response = await fetch(`http://localhost:8080/api/assignMentor`, {
            headers,
            body,
            method: "POST",
            mode: "cors",
            credentials: "same-origin"
        })
        const json = await response.json();
        const postResponse = JSON.stringify(json);
        console.log(postResponse)
        if (response.ok) {
            alert(`Successfully assigned mentor to Student: \n\n${postResponse}`)
            window.location.reload()
        } else {
            alert(`Failed to assign Mentor to Student`)
        }
    };
</script>

<form class="form box" on:submit|preventDefault={assign}>
    <div class="columns">
        <div class="column is-7">
            <MentorDropdown label="Assign: " bind:selectedMentor={mentor} />
        </div>
        <div class="column is-offset-1">
            <button class="button is-small" type="submit" disabled={!mentor}>Submit</button>
        </div>
    </div>
</form>
