<script>
    import { slack, compass, phone, envelope, home} from "svelte-awesome/icons";
    import Icon from "svelte-awesome";

    export let mentor;
</script>

<div class="box">
    <div class="row">
        <div class="column is-6 is-centered">
            A mentor's bio about themselves goes here.
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