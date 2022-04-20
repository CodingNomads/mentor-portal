<script>
    export let bio;
    export let mentorId;
    export let studentId;
    export let updatedBio;

    let mentorUrl = API_BASE_URL + `/api/mentors/${mentorId}`;
    let studentUrl = API_BASE_URL + `/api/students/${studentId}`;

    async function submitUpdate() {
        console.log(typeof(mentorId))
        console.log(typeof(studentId))
        const headers = {
            'Content-type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        // if student being updated
        if (typeof(studentId) === 'number' && typeof(mentorId) === 'undefined') {
            const body = JSON.stringify({
                'userId': studentId,
                'bio': updatedBio
            })
            const response = await fetch(studentUrl, {
                headers,
                body,
                method: 'PUT',
                mode: 'cors',
                credentials: 'same-origin'
            })
            if (response.ok) {
                alert(`Successfully updated bio.`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
        // if mentor being updated
        if (typeof(mentorId) === 'number' && typeof(studentId) === 'undefined') {
            const body = JSON.stringify({
                'userId': mentorId,
                'bio': updatedBio
            })
            const response = await fetch(mentorUrl, {
                headers,
                body,
                method: 'PUT',
                mode: 'cors',
                credentials: 'same-origin'
            })
            if (response.ok) {
                alert(`Successfully updated bio`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
    }

</script>

<div class="box">
    {bio}
</div>
<form class="form" on:submit|preventDefault={submitUpdate}>
    <textarea  class="textarea" bind:value={updatedBio} cols="100" rows="5"></textarea>
    <br>
    <button class="button is-small">Submit</button>
</form>