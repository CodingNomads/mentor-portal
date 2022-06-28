<script>
    import { authorizedApiPostCall } from '../js/apiCalls';
    import InputText from './formInputs/InputText.svelte';
    import CourseDropdown from './formInputs/CourseDropdown.svelte';

    const authToken = sessionStorage.getItem('authToken')

    // post request body/payload
    let firstName = "";
    let lastName = "";
    let location = "";
    let timezoneOffset = "";
    let email = "";
    let telephone = "";
    let courseTrack;
    let bio;

    // post request function
    async function submitStudent() {
        const url = API_BASE_URL + "/api/students"

        const body = JSON.stringify({
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "telephone": telephone,
            "location": location,
            "timezoneOffset": timezoneOffset,
            "courseTrack": courseTrack,
            "bio": bio,
        })
        console.log(body)
        const response = await authorizedApiPostCall(authToken, body, url)
        
        // notify user whether creation was successful
        console.log(response)
        if (response) {
            alert(`Successfully created student.`)
            window.location.reload()
        }
        else {
            alert(`Failed to create student.`)
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
                
                <InputText label="First Name" bind:value={firstName} placeholder="Samwise" required/>
                <InputText label="Last Name" bind:value={lastName} placeholder="Gamjee" required/>
                <InputText label="Email" bind:value={email} placeholder="potatoes@domain.com" required />
                <InputText label="Telephone" bind:value={telephone} placeholder="8007006000" required/>
                <InputText label="Location" bind:value={location} placeholder="Not California" required/>
                <InputText label="Timezone Offset" bind:value={timezoneOffset} placeholder="-5"/>
                <InputText label="Bio" bind:value={bio} placeholder="A hobbit's brief bio." />
                <CourseDropdown label="Course Track" bind:value={courseTrack} required/>
                
                <button class="button" type="submit">Submit</button>
            </form>
        </div>
    </div>
</div>
