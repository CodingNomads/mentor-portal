<script>
    import { onMount } from "svelte";
    import Quill from "../../node_modules/quill/dist/quill.js";

    import FlagDropdown from "./formInputs/FlagDropdown.svelte";
    import DateSelect from "./formInputs/DateSelect.svelte";
    import SupportTypeDropdown from "./formInputs/SupportTypeDropdown.svelte";

    let quill;

    onMount( () => {
        // quill toobar options
        const toolbarOptions = [
            ['bold', 'italic', 'underline', 'strike'],
            [{list: 'ordered'}, {list: 'bullet'}],
            [{size: ['small', false, 'large', 'huge']}],
            [{header: [1, 2, 3, 4, 5, 6, false]}],
            ['blockquote', 'code-block']
        ]
        // quill rich text config
        const createContainer = document.getElementById("createEditor");
        quill = new Quill(createContainer, {
            modules: {
                toolbar: toolbarOptions
            },
            placeholder: "Format your support log entry here.",
            theme: "snow"
        });
    });

    // support log variables
    const mentorId = sessionStorage.getItem("mentorId");
    let log;
    let logDate = new Date();
    let type;
    let duration;
    export let supportLogFlag;
    export let studentId;

    async function postSupportLog () {
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "mentorId": mentorId,
            "studentId": studentId,
            "type": type,
            "flag": supportLogFlag,
            "duration": duration,
            "log": quill.root.innerHTML,
            "logDate": logDate
        })
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
            alert(`Support log submitted.`)
            window.location.reload()
        }
        else {  
            alert(`Support log submission failed.`)
        }
    };
</script>

<svelte:head>
	<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</svelte:head>

<main>
    <form class="form-box" on:submit|preventDefault={postSupportLog}>
        <div class="row">
            <div class="columns">
                <div class="column is-one-fifth">
                    <DateSelect label="Date" bind:value={logDate} /> 
                    <SupportTypeDropdown label="Type" bind:value={type} required /> 
                    <FlagDropdown label="Flag" bind:value={supportLogFlag} required />
                </div>
                <div class="column is-four-fifths">
                    <!-- <textarea class="textarea" bind:value={log} cols="130" rows="9" required></textarea> -->
                    <div id="createEditor" />
                </div>
            </div>
        </div>
        <br>
        <div class="buttons">
            <button class="button is-small">Submit</button>
        </div>
    </form>
</main>