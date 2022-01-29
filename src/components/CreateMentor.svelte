<script>
    // import { onMount } from 'svelte';
    // import FormTemplate from './FormTemplate.svelte';
    import CourseCheckbox from './CourseCheckbox.svelte';
    import InputText from './InputText.svelte';

    // post request body/payload
    let firstName = "";
    let lastName = "";
    let email = "";
    let telephone = "";
    let location = "";
    let jobInfo = "";
    let bio = "";
    let maxStudents = "";
    let proficiencies = "";

    // post response
    let post_response = "";

    // post request function
    async function submitMentor() {
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const body = {
            firstName,
            lastName,
            email,
            telephone,
            location,
            jobInfo,
            bio,
            maxStudents,
            proficiencies
        }
        console.log(body)
        const response = await fetch('http://localhost:8080/api/mentors', {
            headers,
            body,
            method: 'POST',
            mode: 'cors',
            credentials: 'same-origin'
        })
        const json = await response.json()
        post_response = JSON.stringify(json)

        // notify user whether creation was successful
        console.log(post_response)
        if (post_response.status === 200) {
            let createSuccess = post_response.status_text;
            alert(createSuccess)
        }
        else {
            let createFailure = Error(post_response)
            alert(createFailure.status, createFailure.error)
        }
    }
    // async function submitOuterFunction (event) {
    //     const body = JSON.stringify({
    //         firstName: event.detail.firstName,
    //         lastName: event.detail.lastName,
    //         timezoneOffset: event.detail.timezoneOffset,
    //         email: event.detail.email,
    //         telephone: event.detail.telephone,
    //         forumUsername: event.detail.forumUsername,
    //         slackUsername: event.detail.slackUsername,
    //         username: event.detail.username,
    //         passwordHash: event.detail.passwordHash,
    //         isAdmin: event.detail.isAdmin
    //     })
    //     const headers = {
    //         'Content-Type': 'application/json',
    //         'Access-Control-Allow-Origin': '*'
    //     }
    //     console.log(body)
    //     const response = await fetch('http://localhost:8080/api/mentors', {
    //         headers,
    //         body,
    //         method: 'POST',
    //         mode: 'cors',
    //         credentials: 'same-origin',
    //     })
    //     const json = await response.json()
    //     post_result = JSON.stringify(json)
    //     return post_result
    // } 
</script>

<div class="container">
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- mentor data to bind to post request -->
            <form class="form box" on:submit|preventDefault={submitMentor}>
                <h1>Create Mentor</h1>
                <hr>
                
                <InputText label="First Name" bind:value={firstName} placeholder="Ryan" />
                <InputText label="Last Name" bind:value={lastName} placeholder="Desmond" />
                <InputText label="Email" bind:value={email} placeholder="mentoremail@domain.com" />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000"/>
                <InputText label="Location" bind:value={location} placeholder="California" />
                <InputText label="Job Info" bind:value={jobInfo} placeholder="Co-founder of CodingNomads." />
                <InputText label="Bio" bind:value={bio}  placeholder="A few sentences about this mentor here." />
                <InputText label="Max Students" bind:value={maxStudents} placeholder="10" />
                <CourseCheckbox label="Proficiencies" bind:checked={proficiencies} />
                
                <button class="button" type="submit" on:click={submitMentor}>Submit</button>
            </form>
        </div>
    </div>
</div>

<!-- <div class="container">
    <FormTemplate 
        formTitleItem = {"Create Mentor"}
        submitCTA="Submit"
        on:formSubmit={submitOuterFunction}
    />
</div> -->
