<script>
    import { onMount, onDestroy } from "svelte"; 
    import NavBar from "../../components/NavBar.svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";

    export let mentorList = [];
    export let filteredMentors = [];   

    // authorization
    const authToken = sessionStorage.getItem("authToken")

    let searchMentors = (e) => {
        let searchString = e.target.value
        filteredMentors = mentorList.filter(mentor => {
            let firstName = mentor.firstName
            let lastName = mentor.lastName
            let fullName = mentor.firstName + " " + mentor.lastName
            return firstName.includes(searchString) || lastName.includes(searchString) || fullName.includes(searchString)
        });
    };

    onMount(async () => {
        const url = API_BASE_URL + "/api/mentors"
        mentorList = await authorizedApiGetCall(authToken, url)
    })

    onDestroy(mentorList, filteredMentors)
</script>


<div class="container">
    <NavBar />
    <br>
    <!-- title and search box -->
    <div class="row">
        <div class="columns">
            <div class="column is-one-fifth">
                <h1 class="title is-1"><strong>Mentors</strong></h1>
            </div>
            <div class="column is-5">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchMentors} placeholder="spacebar + backspace to populate" />
            </div>
        </div>
    </div>          
    <br>
    <!-- filtered mentor list -->
    {#if filteredMentors.length > 0}
        {#each filteredMentors as mentor (mentor.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-2 is-offset-one-fifth">
                        <!-- mentor name -->
                        <div class="row">
                            <a href="/mentors/{mentor.id}" class="button is-link is-medium"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
                        </div>
                        <!-- quick view of proficiencies -->
                        <div class="row">
                            {#each mentor.proficiencies as proficiency}
                            <span class="tag is-dark">{proficiency} </span>
                            {/each}
                        </div>
                        <!-- flagged status -->
                        <br>
                        <div class="row">
                            {#if mentor.flag === true }
                            <span class="tag is-danger">Flagged</span>
                            {/if}
                        </div>
                    </div>
                    <!-- offset student count view -->
                    <div class="column is-offset-1 is-4">
                        <div class="row">
                            <progress class="progress is-small is-info" value={mentor.studentCount} max={mentor.maxStudents}></progress>
                        </div>
                        <div class="row">
                            <p><em>{mentor.studentCount}/{mentor.maxStudents} students</em></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="column is-three-fifths is-offset-one-fifth">
                <hr>
            </div>
        {/each}
    {/if}
</div>
