<script>
    import { slack, compass, phone, envelope, home} from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import UpdateBio from "../formInputs/UpdateBio.svelte";

    export let mentor;
    let editTrigger = false;

    const editBio = () => {
        return editTrigger = true
    };
    const cancelEdit = () => {
        return editTrigger = false
    };
</script>

<div class="box">
    <div class="row">
        <div class="column is-6 is-centered">
            {#if editTrigger === false}
            {mentor.bio}
            <br>
            <br>
            <button class="button is-small" on:click={editBio}>Edit</button>
            {:else}   
            <UpdateBio mentorId={mentor.id} bio={mentor.bio} />
            <br>
            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
            {/if}
        </div>
    </div>
</div>

<ul>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={envelope} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.email}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={phone} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.telephone}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={slack} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.slackUsername}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={compass} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.forumUsername}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={home} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.location}</p>
        </div>
    </div>
</ul>   