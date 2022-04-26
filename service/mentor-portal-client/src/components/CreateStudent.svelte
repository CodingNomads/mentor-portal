<script>
    import CourseCheckbox from './formInputs/CourseCheckbox.svelte';
    import DateSelect from './formInputs/DateSelect.svelte';
    import MentorDropdown from './formInputs/MentorDropdown.svelte';
    import InputText from './formInputs/InputText.svelte';
import MentorshipDropdown from './formInputs/MentorshipDropdown.svelte';
import CourseDropdown from './formInputs/CourseDropdown.svelte';

    // post request body/payload
    let firstName = "";
    let lastName = "";
    let location = "";
    let timezoneOffset = "";
    let email = "";
    let forumUsername = "";
    let slackUsername = "";
    let telephone = "";
    let courseTrack = "";
    let mentorshipOption = "";
    let bio = "";
    // let startDate = "";
    // let endDate = "";
    let assignedMentors = "";

    // post request function
    async function submitStudent() {
        const url = API_BASE_URL + "/api/students"
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        const body = JSON.stringify({
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "forumUsername": forumUsername,
            "slackUsername": slackUsername,
            "telephone": telephone,
            "location": location,
            "timezoneOffset": timezoneOffset,
            "courseTrack": courseTrack,
            "mentorshipOption": mentorshipOption,
            "bio": bio,
            // startDate,
            // endDate,
            "assignedMentors": assignedMentors
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
            alert(`Successfully created student.`)
            window.location.reload()
        }
        else {
            alert(`Failed to create student.\n\n${Error(postResponse)}`)
        }
    } 
</script>

<div class="container">
    <div class="columns is-left">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- student data to bind to post request -->
            <form class="form box" on:submit|preventDefault={submitStudent}>
                <h1>Create Student</h1>
                <hr>
                
                <InputText label="First Name" bind:value={firstName} placeholder="Samwise" />
                <InputText label="Last Name" bind:value={lastName} placeholder="Gamjee" />
                <InputText label="Email" bind:value={email} placeholder="potatoes@domain.com" />
                <InputText label="Forum Username" bind:value={forumUsername} placeholder="boilthem" />
                <InputText label="Slack Username" bind:value={slackUsername} placeholder="mashthem" />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000"/>
                <InputText label="Location" bind:value={location} placeholder="Not California" />
                <InputText label="Timezone Offset" bind:value={timezoneOffset} placeholder="-5"/>
                <InputText label="Bio" bind:value={bio} placeholder="A hobbit that likes stews" />
                <CourseDropdown label="Course Track" bind:value={courseTrack} />
                <MentorshipDropdown label="Mentorship Option" bind:value={mentorshipOption} />
                <!-- <DateSelect label="Start Date" bind:value={startDate} /> -->
                <!-- <DateSelect label="End Date" bind:value={endDate} /> -->
                <MentorDropdown label="Assign Mentor" bind:value={assignedMentors} />
                
                <button class="button" type="submit">Submit</button>
            </form>
        </div>
    </div>
</div>
