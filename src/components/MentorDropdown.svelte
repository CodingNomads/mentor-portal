<script>
    import { onMount } from "svelte";
    import { capitolize } from "../js/Capitolize";
    export let label = "";
    export let value = "";
    export let mentorList = "";

    onMount(async function getMentors() {
        const response = await fetch("http://localhost:8080/api/mentors", {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentorList = await response.json()
        console.log(mentorList)
    })
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select name={label} id={label} bind:value>
    {#each mentorList as mentor}
        <option value={mentor}>{mentor.firstName + " " + mentor.lastName}</option>
    {/each}
    </select>
</div>