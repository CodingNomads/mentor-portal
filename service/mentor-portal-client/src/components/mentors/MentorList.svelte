<script>
    import { onMount, onDestroy } from "svelte"; 
    import NavBar from "../../components/NavBar.svelte";

    export let mentorList = [];
    export let filteredMentors = [];   

    let searchMentors = (e) => {
        const searchString = e.target.value
        filteredMentors = mentorList.filter(mentor => {
           return mentor.firstName.includes(searchString) || mentor.lastName.includes(searchString)
        });
    };

    onMount(async () => {
        const url = API_BASE_URL + "/api/mentors"
        const response = await fetch(url, {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentorList = await response.json()
        // console.log(mentorList)
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
            <div class="column is-4">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchMentors} placeholder="Search Mentor" />
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
                            <a href="/mentors/{mentor.id}" class="button is-info is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
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
    <!-- original mentor list -->
    {:else}
        {#each mentorList as mentor (mentor.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-2 is-offset-one-fifth">
                        <!-- mentor name -->
                        <div class="row">
                            <a href="/mentors/{mentor.id}" class="button is-info is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
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
