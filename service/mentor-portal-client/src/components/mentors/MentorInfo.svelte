<script>
    import { phone, envelope, home} from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import UpdateBio from "../formInputs/UpdateBio.svelte";

    export let mentor;

    const isAdmin = sessionStorage.getItem("isAdmin")
    let adminCheck;
    let editTrigger;

    function editBio(isAdmin, editThis) {
        adminCheck = isAdmin;
        editTrigger = editThis;
        return adminCheck && editTrigger
    };
    const cancelEdit = () => {
        adminCheck = "";
        editTrigger = false;
        return adminCheck && editTrigger
    };
</script>

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
                <i><span><Icon data={home} scale={2} /></span></i>
            </div>
            <p class="is-medium">{mentor.location}</p>
        </div>
    </div>
</ul>   

<br>

<table class="table is-fullwidth">
    <tbody>
        <tr>
            <div class="box">
                <div class="columns">
                    <div class="column is-8 is-centered">
                        <div class="content">
                            {@html mentor.bio}
                        </div>
                        <br>
                        <!-- restrict bio edit to admin -->
                        {#if isAdmin === "true"}
                            <button class="button is-small" on:click={() => editBio(isAdmin, true)}>Edit</button>
                        {/if}
                        {#if adminCheck === isAdmin && editTrigger === true}   
                            <UpdateBio mentorId={mentor.id} bio={mentor.bio} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        {/if}
                    </div>
                </div>
            </div>
        </tr>
    </tbody>
</table>
<br>