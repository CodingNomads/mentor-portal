<script>
    import NavBar from "../components/NavBar.svelte";
    import { onMount } from "svelte";

    let mentor_list = "";

    onMount(async () => {
        const response = await fetch("http://localhost:8080/api/mentors", {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentor_list = await response.json()
        console.log(mentor_list)
    })
</script>

<main>
    <div class="container">
        <NavBar />
        <div class="columns-is-centered">
            <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
                {#each mentor_list as mentor }
                <div class="card-body">
                    <a href="#/" class="card-title"><strong>{mentor.lastName}, {mentor.firstName}</strong></a>
                    <p>{mentor.email}</p>
                    <p>{mentor.telephone}</p>
                    <hr>
                </div>
                {/each}
            </div>
        </div>
    </div>
</main>