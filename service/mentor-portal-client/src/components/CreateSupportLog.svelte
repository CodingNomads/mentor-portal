<script>
    import FlagDropdown from "./formInputs/FlagDropdown.svelte";
    import DateSelect from "./formInputs/DateSelect.svelte";
    import InputText from "./formInputs/InputText.svelte";
    import MentorDropdown from "./formInputs/MentorDropdown.svelte";
    import SupportTypeDropdown from "./formInputs/SupportTypeDropdown.svelte";

    // support log variables
    let log;
    let logDate;
    let type;
    let duration;
    export let supportLogFlag;
    export let selectedMentor;
    export let studentId;

    async function postSupportLog () {
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "mentorId": selectedMentor.id,
            "studentId": studentId,
            "type": type,
            "flag": supportLogFlag,
            "duration": duration,
            "log": log,
            "logDate": logDate
        })
        console.log(body)
        const url = API_BASE_URL + `/api/supportLog`
        const response = await fetch(url, {
            headers,
            body,
            method: "POST",
            mode: "cors",
            credentials: "same-origin"
        })
        const json = await response.json()
        const postResponse = JSON.stringify(json)

        if (response.ok) {
            alert(`Support log submitted: \n\n${postResponse}`)
            // updateFlag()
            window.location.reload()
        }
        else {  
            alert(`Support log submission failed.`)
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
                <FlagDropdown label="Flag" bind:value={supportLogFlag} />
                <InputText class="input is-small" placeholder="20" label="Duration (minutes)" bind:value={duration} />
            </div>
            <div class="column is-four-fifths">
                <textarea class="textarea" bind:value={log} cols="130" rows="9"></textarea>
            </div>
        </div>
    </div>
    <br>
    <div class="buttons">
        <button class="button is-small">Submit</button>
    </div>
</form>