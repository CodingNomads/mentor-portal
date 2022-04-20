<script>
    // import { onMount } from 'svelte';
    // import FormTemplate from './FormTemplate.svelte';
    import CourseCheckbox from './formInputs/CourseCheckbox.svelte';
    import InputText from './formInputs/InputText.svelte';

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
        const response = await fetch(url, {
            headers,
            body,
            method: 'POST',
            mode: 'cors',
            credentials: 'same-origin'
        })
        const json = await response.json()
        const postResponse = JSON.stringify(json)

        // notify user whether creation was successful
        console.log(postResponse)
        if (response.ok) {
            alert(`Successfully created mentor.\n\n${postResponse}`)
            window.location.reload()
        }
        else {
            alert(`Failed to create mentor.\n\n${Error(postResponse)}`)
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
                
                <InputText label="First Name" bind:value={firstName} placeholder="Frodo" />
                <InputText label="Last Name" bind:value={lastName} placeholder="Baggins" />
                <InputText label="Email" bind:value={email} placeholder="frodofromtheshire@domain.com" />
                <InputText label="Forum Username" bind:value={forumUsername} placeholder="littleHobbit666" />
                <InputText label="Slack Username" bind:value={slackUsername} placeholder="frodoslack" />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000"/>
                <InputText label="Location" bind:value={location} placeholder="Mordor" />
                <InputText label="Timezone Offset" bind:value={timezoneOffset} placeholder="-6" />
                <InputText label="Bio" bind:value={bio}  placeholder="Just a wee hobbit from the shire that knows data science" />
                <InputText label="Max Students" bind:value={maxStudents} placeholder="10" />
                <CourseCheckbox label="Proficiencies" bind:checked={proficiencies} />
                
                <button class="button" type="submit">Submit</button>
            </form>
        </div>
    </div>
</div>
