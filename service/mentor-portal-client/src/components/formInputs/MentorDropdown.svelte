<script>
    import { onMount } from "svelte";
    import { capitolize } from "../../js/capitolize";
    export let label = "";
    export let mentorList = "";
    export let value;

    onMount(async function getMentors() {
        const url = API_BASE_URL + "/api/mentors"
        const response = await fetch(url, {
            mode: 'cors',
            credentials: "same-origin"
        })
        mentorList = await response.json()
        // console.log(mentorList)
    })
</script>

<div class="field">
    <label for={label} class="label">{capitolize(label)}</label>
    <select bind:value>
        {#each mentorList as mentor (mentor.id)}
            <option class="dropdown item" type="text" value={mentor}>{mentor.firstName + " " + mentor.lastName}</option>
        {/each}
    </select>
</div>