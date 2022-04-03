<script>
    export let studentId;
    export let mentorId;
    export let flag;
    
    const mentorEndpoint = `http://localhost:8080/api/mentors/${mentorId}`;
    const studentEndpoint = `http://localhost:8080/api/students/${studentId}`

    async function updateFlag() {
        // submit user flag update to DB
        console.log(typeof(mentorId))
        console.log(typeof(studentId))
        const headers = {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
        if (typeof(studentId) === 'number' && typeof(mentorId) === 'undefined') {
            const body = JSON.stringify({
                "flag": flag,
                "userId": studentId
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
                alert(`Updated flag to: \n\n${flag}`)
                window.location.reload()
            } else{
                alert(`Failed to change flag value.`)
            }
        } 
        if (typeof(mentorId) === 'number' && typeof(studentId) === 'undefined') {
            const body = JSON.stringify({
                "flag": flag,
                "userId": mentorId
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
                alert(`Updated flag to: \n\n${flag}`)
            } else{
                alert(`Failed to change flag value.`)
            }
        }
    };
</script>

<form class="form" on:submit|preventDefault={updateFlag}>
    <button class="tag is-delete"></button>
</form>
