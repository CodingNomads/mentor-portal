<script>
    export let studentId;
    export let flag;

    async function updateFlag() {
        // submit user flag update to DB
        console.log(`flag value: ${flag}`)
        console.log(`student id: ${studentId}`)
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        const body = JSON.stringify({
            "flag": flag,
            "studentId": studentId
        })
        console.log(body)
        const response = await fetch(`http://localhost:8080/api/students/${studentId}`, {
            headers,
            body,
            method: "PUT",
            mode: "cors",
            credentials: "same-origin"
        });
        if (response.ok){
            alert(`Updated flag to: \n\n${flag}`)
        } else{
            alert(`Failed to change flag value.`)
        }
    };
</script>

<form class="form" on:submit|preventDefault={updateFlag}>
    <button class="tag is-delete"></button>
</form>
