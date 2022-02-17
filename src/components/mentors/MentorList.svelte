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
        console.log(filteredMentors)
    };

    onMount(async () => {
        const response = await fetch("http://localhost:8080/api/mentors", {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentorList = await response.json()
        console.log(mentorList)
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
                <h1 class="title is-1"><strong>Mentors:</strong></h1>
            </div>
            <div class="column is-two-fifths is-justify-content-left">
                <input class="input is-info is-medium" type="text" id="searchBar" on:keyup={searchMentors} placeholder="Search Mentor" />
            </div>
        </div>
    </div>          
    <br>
    <!-- mentor list -->
    <div class="row">
    {#if filteredMentors.length > 0}
        {#each filteredMentors as mentor (mentor.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-offset-one-fifth">
                        <ul>
                            <a href="/mentors/{mentor.id}" class="button is-info is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
                            <p class="card-text">{mentor.email}</p>
                            <p class="card-text">{mentor.telephone}</p>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
        {/each}
    {:else}
        {#each mentorList as mentor (mentor.id)}
            <div class="row">
                <div class="columns">
                    <div class="column is-offset-one-fifth">
                        <ul>
                            <a href="/mentors/{mentor.id}" class="button is-info is-small"><strong>{mentor.firstName} {mentor.lastName}</strong></a>
                            <p class="card-text">{mentor.email}</p>
                            <p class="card-text">{mentor.telephone}</p>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
        {/each}
    {/if}
    </div>
</div>
