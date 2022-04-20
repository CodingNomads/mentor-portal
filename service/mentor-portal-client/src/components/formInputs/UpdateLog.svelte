<script>
    import FlagDropdown from "../formInputs/FlagDropdown.svelte";

    export let userId;
    export let supportLogId;
    export let log;
    let updatedLog;
    export let flag;

    async function submitUpdates() {
        const compiledLog = log + " UPDATE: " + updatedLog
        const url = API_BASE_URL + `/api/${userId}/supportLogs/${supportLogId}`
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "studentId": userId,
            "supportLogId": supportLogId,
            "flag": flag,
            "log": compiledLog
        })
        const response = await fetch(url,{
            headers,
            body,
            method: "PUT",
            mode: "cors",
            credentials: "same-origin"
        })
        if (response.ok) {
            alert(`Successfully updated support log.`)
            window.location.reload()
        } else{
            alert(`Failed to update support log.`)
        }
    };
    
</script>
<div class="box">
    {log}
</div>
<form class="form" on:submit|preventDefault={submitUpdates}>
    <FlagDropdown label="Flag" bind:value={flag} />
    <textarea label="Updated Log" class="textarea" bind:value={updatedLog} cols="100" rows="5"></textarea>
    <br>
    <button class="button is-small">Submit</button>
</form>