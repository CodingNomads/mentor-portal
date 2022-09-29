<script>
    import { onMount, onDestroy } from "svelte"; 
    import NavBar from "../../components/NavBar.svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";
    import Icon from 'svelte-awesome';
    import { flag } from "svelte-awesome/icons";

    export let mentorList = [];
    export let filteredMentors = [];   

    // authorization
    const authToken = sessionStorage.getItem("authToken")

    let searchMentors = (e) => {
        let searchString = e.target.value.toLowerCase()
        filteredMentors = mentorList.filter(mentor => {
            let firstName = mentor.firstName.toLowerCase()
            let lastName = mentor.lastName.toLowerCase()
            let fullName = firstName + " " + lastName
            return firstName.includes(searchString) || lastName.includes(searchString) || fullName.includes(searchString)
        });
    };

    onMount(async () => {
        const url = API_BASE_URL + "/api/mentors"
        mentorList = await authorizedApiGetCall(authToken, url)
        mentorList.sort((a, b) => a.firstName.localeCompare(b.firstName))
        filteredMentors = mentorList
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
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchMentors} placeholder="Enter mentor name." />
            </div>
        </div>
    </div>          
    <br>
    <!-- mentor table -->
    <table class="table is-fullwidth is-bordered">
        <thead>
            <tr>
                <th>Full Name</th>
                <th>Mentor Proficiencies</th>
                <th>Student Count</th>
            </tr>
        </thead>
        <tbody>
            {#each filteredMentors as mentor}
                <tr>
                    <td>
                        {#if mentor.flag}
                            <div class="row">
                                <div class="columns">
                                    <div class="column is-1">
                                        <Icon data={flag} style="color:red"/>
                                    </div>
                                    <div class="column">
                                        <a href="/mentors/{mentor.id}" class="is-link is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
                                    </div>
                                </div>
                            </div>
                        {:else}
                            <div class="row">
                                <div class="columns">
                                    <div class="column is-1">
                                        <!-- spacer -->
                                    </div>
                                    <div class="column">
                                        <a href="/mentors/{mentor.id}" class="is-link is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
                                    </div>
                                </div>
                            </div>
                        {/if}
                    </td>
                    <td>
                        {#each mentor.proficiencies as proficiency}
                            {proficiency}
                            <br>
                        {/each}
                    </td>
                    <td>
                        <div class="row">
                            <progress class="progress is-small is-info" value={mentor.studentCount} max={mentor.maxStudents}></progress>
                        </div>
                        <div class="row">
                            <p><em>{mentor.studentCount}/{mentor.maxStudents} students</em></p>
                        </div>
                    </td>
                </tr>
            {/each}
        </tbody>
    </table>
</div>
