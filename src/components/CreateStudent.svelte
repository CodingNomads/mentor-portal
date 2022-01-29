<script>
    import CourseCheckbox from './CourseCheckbox.svelte';
    import MentorDropdown from './MentorDropdown.svelte';
    import InputText from './InputText.svelte';

    // post request body/payload
    let firstName = "";
    let lastName = "";
    let location = "";
    let email = "";
    let telephone = "";
    let courseTrack = "";
    let bio = "";
    let startDate = "";
    let endDate = "";
    let mentor = "";

    // post response
    let post_response = "";

    // post request function
    async function submitStudent() {
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
            courseTrack,
            bio,
            startDate,
            endDate,
            mentor
        }
        console.log(body)
        const response = await fetch('http://localhost:8080/api/students', {
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
</script>

<div class="container">
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- student data to bind to post request -->
            <form class="form box" on:submit|preventDefault={submitStudent}>
                <h1>Create Student</h1>
                <hr>
                
                <InputText label="First Name" bind:value={firstName} placeholder="Ryan" />
                <InputText label="Last Name" bind:value={lastName} placeholder="Desmond" />
                <InputText label="Email" bind:value={email} placeholder="studentemail@domain.com" />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000"/>
                <InputText label="Location" bind:value={location} placeholder="California" />
                <CourseCheckbox label="Course Track" bind:checked={courseTrack} />
                <InputText label="Bio" bind:value={bio} placeholder="A few sentences about this student here." />
                <!-- <InputText label="Start Date" bind:value={startDate} placeholder="" /> -->
                <!-- <InputText label="End Data" bind:value={endDate} placeholder="Python, Java" /> -->
                <MentorDropdown label="Assign Mentor" bind:value={mentor} />
                
                <button class="button" type="submit" on:click={submitStudent}>Submit</button>
            </form>
        </div>
    </div>
</div>
