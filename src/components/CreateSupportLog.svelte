<script>
    import { onMount } from "svelte";

    import DateSelect from "./formInputs/DateSelect.svelte";
    import InputText from "./formInputs/InputText.svelte";
    import MentorDropdown from "./formInputs/MentorDropdown.svelte";
    import SupportTypeDropdown from "./formInputs/SupportTypeDropdown.svelte";

    let log;
    let logDate;
    let selectedMentor;
    export let studentId;
    let type;
    let duration;

    async function postSupportLog () {
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "mentorId": selectedMentor.id,
            "studentId": studentId,
            "type": type,
            "duration": duration,
            "log": log,
            "logDate": logDate
        })
        console.log(body)
        const response = await fetch("http://localhost:8080/api/supportLog", {
            headers,
            body,
            method: "POST",
            mode: "cors",
            credentials: "same-origin"
        })
        const json = await response.json()
        const postResponse = JSON.stringify(json)
        console.log(postResponse)

        if (postResponse) {
            alert(`Support log submitted: \n\n${postResponse}`)
        }
        else {
            let createFailure = Error(postResponse)
            alert(`Support log submission failed :(. \n\n${createFailure.status, createFailure.error}`)
        }
    };
</script>

<form class="form-box" on:submit|preventDefault={postSupportLog}>
    <div class="row">
        <div class="columns">
            <div class="column is-one-fifth">
                <DateSelect label="Date" bind:value={logDate} /> 
                <MentorDropdown label="Mentor" bind:selectedMentor={selectedMentor} />
                <SupportTypeDropdown label="Type" bind:value={type} /> 
                <InputText class="input is-small" placeholder="20" label="Duration (minutes)" bind:value={duration} />
            </div>
            <div class="column is-four-fifths">
                <textarea class="textarea" bind:value={log} cols="130" rows="9"></textarea>
            </div>
        </div>
    </div>
    <br>
    <div class="buttons">
        <button class="button is-success">Submit</button>
    </div>
</form>