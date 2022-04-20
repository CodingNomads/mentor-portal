<script>
    export let studentId;
    export let mentorId;
    export let flag;
    
    const mentorEndpoint = API_BASE_URL + `/api/mentors/${mentorId}`;
    const studentEndpoint = API_BASE_URL + `/api/students/${studentId}`;

    async function updateFlag() {
        // submit user flag update to DB
        console.log(typeof(mentorId))
        console.log(typeof(studentId))
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        // if student flag being updated
        if (typeof(studentId) === 'number') {
            const body = JSON.stringify({
                "userId": studentId,
                "flag": flag
            })
            console.log(body)
            const response = await fetch(studentEndpoint, {
                headers,
                body,
                method: "PUT",
                mode: "cors",
                credentials: "same-origin"
            });
            if (response.ok){
                alert(`Updated flag to: ${flag}`)
                window.location.reload()
            } else{
                alert(`Failed to change flag value.`)
            }
        }
        // if mentor flag being updated 
        if (typeof(mentorId) === 'number') {
            const body = JSON.stringify({
                "userId": mentorId,
                "flag": flag,
            })
            console.log(body)
            const response = await fetch(mentorEndpoint, {
                headers,
                body,
                method: "PUT",
                mode: "cors",
                credentials: "same-origin"
            });
            if (response.ok){
                alert(`Updated flag to: ${flag}`)
                window.location.reload()
            } else{
                alert(`Failed to change flag value.`)
            }
        }
    };
</script>

{#if flag === true}
<form class="form" on:submit|preventDefault={updateFlag}>
    <button class="button is-small">Rasie Flag</button>
</form>
{:else}
<div class="tags has-addons">
    <span class="tag is-danger">Flagged</span>
    <form class="form" on:submit|preventDefault={updateFlag}>
        <button class="tag is-delete"></button>
    </form>
</div>
{/if}
