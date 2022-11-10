<script>
    import { envelope, phone, home } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";
    import UpdateBio from "../formInputs/UpdateBio.svelte";

    const isAdmin = sessionStorage.getItem("isAdmin")
    let adminCheck;
    let editTrigger;

    export let student;

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

<svelte:head>
	<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</svelte:head>

<ul>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={envelope} scale={2} /></span></i>
            </div>
            <p class="is-medium">{student.email}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={phone} scale={2} /></span></i>
            </div>
            <p class="is-medium">{student.telephone}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={home} scale={2} /></span></i>
            </div>
            <p class="is-medium">{student.location}</p>
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
                            {@html student.bio}
                        </div>
                        <br>
                        <!-- restrict edits to admin -->
                        {#if isAdmin === "true"} 
                            <button class="button is-small" on:click={() => editBio(isAdmin, true)}>Edit</button>
                        {/if}
                        {#if adminCheck === isAdmin && editTrigger === true}
                            <UpdateBio studentId={student.id} bio={student.bio} />
                            <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                        {/if}
                    </div>
                </div>
            </div>
        </tr>
    </tbody>
</table>
<br>