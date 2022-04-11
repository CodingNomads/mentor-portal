<script>
    import InputText from './InputText.svelte'
    export let formTitleItem = "";
    export let submitCTA = "";

    // const dispatch = createEventDispatcher();
    // function submitInnerFunction() {
    //     const form_data = {
    //         firstName,
    //         lastName,
    //         email,
    //         telephone,
    //         location,
    //         job_info,
    //         bio,
    //         max_students,
    //         proficiencies
    //     }
    //     dispatch('formSubmit', form_data);
    // }

    let submitFunction = "";

    // post request body
    let firstName = "";
    let lastName = "";
    let email = "";
    let telephone = "";
    let location = "";
    let job_info = "";
    let bio = "";
    let max_students = "";
    let proficiencies = "";
</script>

<div class="container">
    <div class="columns is-centered">
        <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
            <!-- database table data to post -->
            <form class="form box" on:submit|preventDefault={submitFunction}>
                <h1>{formTitleItem}</h1>
                <hr>
                
                <InputText label="First Name" bind:value={firstName} placeholder="Ryan" />
                <InputText label="Last Name" bind:value={lastName} placeholder="Desmond" />
                <InputText label="Email" bind:value={email} placeholder="someemail@domain.com" />
                <InputText label="telephone" bind:value={telephone} placeholder="8007006000"/>
                <InputText label="Location" bind:value={location} placeholder="California" />
                <InputText label="Job Info" bind:value={job_info} placeholder="Co-founder of CodingNomads." />
                <InputText label="Bio" bind:value={bio}  placeholder="A few sentences about this mentor here." />
                <InputText label="Max Students" bind:value={max_students} placeholder="10" />
                <InputText label="Proficiencies" bind:value={proficiencies} placeholder="Python, Java" />
                
                <button class="button" type="submit" on:click={submitFunction}>{submitCTA}</button>
            </form>
        </div>
    </div>
</div>