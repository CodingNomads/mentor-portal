<script>
    import { envelope, phone, slack, compass, home } from "svelte-awesome/icons";
    import Icon from "svelte-awesome";

    export let student;
</script>

<div class="box">
    <div class="row">
        <div class="column is-6 is-centered">
            A students's bio/primary goal goes here.
        </div>
    </div>
</div>

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
                <i><span><Icon data={slack} scale={2} /></span></i>
            </div>
            <p class="is-medium">{student.slackUsername}</p>
        </div>
    </div>
    <div class="row">
        <div class="tags has-addons">
            <div class="column is-1">
                <i><span><Icon data={compass} scale={2} /></span></i>
            </div>
            <p class="is-medium">{student.forumUsername}</p>
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