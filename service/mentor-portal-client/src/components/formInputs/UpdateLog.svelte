<script>
    import { onMount } from "svelte";
    import Quill from "../../../node_modules/quill/dist/quill.js"

    import { authorizedApiPutCall } from "../../js/apiCalls.js";
    import FlagDropdown from "../formInputs/FlagDropdown.svelte";

    const authToken = sessionStorage.getItem("authToken")
    export let userId;
    export let supportLogId;
    export let log;
    export let flag;
    let quill;

    // quill rich text config
    onMount( () => {
        // quill toobar options
        const toolbarOptions = [
            ['bold', 'italic', 'underline', 'strike'],
            [{list: 'ordered'}, {list: 'bullet'}],
            [{size: ['small', false, 'large', 'huge']}],
            [{header: [1, 2, 3, 4, 5, 6, false]}],
            ['blockquote', 'code-block']
        ]
        // quill editor setup
        const updateContainer = document.getElementById("updateEditor");
        quill = new Quill(updateContainer, {
            modules: {
                toolbar: toolbarOptions
            },
            placeholder: "Format your support log entry here.",
            theme: "snow"
        });
        const delta = quill.clipboard.convert(log)
        quill.setContents(delta, 'silent')
    });


    async function submitUpdates() {
        const compiledLog = quill.root.innerHTML
        const url = API_BASE_URL + `/api/${userId}/supportLogs/${supportLogId}`
        const body = JSON.stringify({
            "studentId": userId,
            "supportLogId": supportLogId,
            "flag": flag,
            "log": compiledLog
        })
        const response = await authorizedApiPutCall(authToken, body, url)
     
        if (response.status === 200) {
            alert(`Successfully updated support log.`)
            window.location.reload()
        } else{
            alert(`Failed to update support log.`)
        }
    };
    
</script>

<svelte:head>
	<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</svelte:head>

<form class="form" on:submit|preventDefault={submitUpdates}>
    <FlagDropdown label="Flag" bind:value={flag} />
    <div id="updateEditor" />
    <br>
    <button class="button is-small">Submit</button>
</form>