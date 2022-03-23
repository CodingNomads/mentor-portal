<script>
    import { onMount } from "svelte";
    import { capitolize } from "../../js/Capitolize";
    export let label = "";
    export let mentorList = "";
    export let selectedMentor;

    onMount(async function getMentors() {
        const response = await fetch("http://localhost:8080/api/mentors", {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentorList = await response.json()
        // console.log(mentorList)
    })
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select bind:value={selectedMentor}>
        {#each mentorList as mentor (mentor.id)}
            <option class="dropdown item" type="text" value={mentor}>{mentor.firstName + " " + mentor.lastName}</option>
        {/each}
    </select>
</div>