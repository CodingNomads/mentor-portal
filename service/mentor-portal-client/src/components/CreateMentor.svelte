<script>
    import { authorizedApiPostCall } from '../js/apiCalls';

    import CourseCheckbox from './formInputs/CourseCheckbox.svelte';
    import InputText from './formInputs/InputText.svelte';

    const authToken = sessionStorage.getItem('authToken')

    // post request body/payload
    let firstName = "";
    let lastName = "";
    let email = "";
    let forumUsername = "";
    let slackUsername = "";
    let telephone = "";
    let location = "";
    let timezoneOffset = "";
    let jobInfo = "";
    let bio = "";
    let maxStudents = "";
    let proficiencies = "";

    // post request function
    async function submitMentor() {
        const url = API_BASE_URL + "/api/mentors"
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const body = JSON.stringify({
            firstName,
            lastName,
            timezoneOffset,
            email,
            forumUsername,
            slackUsername,
            telephone,
            location,
            jobInfo,
            bio,
            maxStudents,
            proficiencies
        })
        console.log(body)
        const response = await authorizedApiPostCall(authToken, body, url)

        // notify user whether creation was successful
        console.log(response)
        if (response) {
            alert(`Successfully created mentor`)
            window.location.reload()
        }
        else {
            alert(`Failed to create mentor`)
        }
    }
</script>

<div class="container">
    <div class="columns is-left">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- mentor data to bind to post request -->
            <form class="form box" on:submit|preventDefault={submitMentor}>
                <h1>Create Mentor</h1>
                <hr>
                
                <InputText label="First Name" bind:value={firstName} placeholder="Frodo" required />
                <InputText label="Last Name" bind:value={lastName} placeholder="Baggins" required />
                <InputText label="Email" bind:value={email} placeholder="frodofromtheshire@domain.com" required />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000" required />
                <InputText label="Location" bind:value={location} placeholder="Mordor" required />
                <InputText label="Timezone Offset" bind:value={timezoneOffset} placeholder="-6" />
                <InputText label="Bio" bind:value={bio}  placeholder="Just a wee hobbit from the shire that knows data science" />
                <InputText label="Max Students" bind:value={maxStudents} placeholder="10" />
                <CourseCheckbox label="Proficiencies" bind:checked={proficiencies} required />
                
                <button class="button" type="submit">Submit</button>
            </form>
        </div>
    </div>
</div>
