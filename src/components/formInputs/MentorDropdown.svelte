<script>
    import { onMount } from "svelte";
    import Icon from "svelte-awesome";
    import { angleDown } from "svelte-awesome/icons";
    import { capitolize } from "../../js/Capitolize";
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


<!-- <div class="dropdown">
    <div class="dropdown-trigger">
        <button class="button is-small" aria-controls="dropdown-menu">
            <span>Mentor </span>
            <Icon data={angleDown} />
        </button>
    </div>
    <div class="dropdown-menu" id="dropdown-menu" role="menu">
        <div class="dropdown-content"> -->
            <label for={label} class="label">{capitolize(label)}</label>
            <!-- <Icon data={angleDown} /> -->
            <select span="Mentor" name={label} id={label} bind:value>
                {#each mentorList as mentor}
                    <option class="dropdown-item" value={mentor}>{mentor.firstName + " " + mentor.lastName}</option>
                {/each}
            </select>
        <!-- </div>
    </div>
</div> -->