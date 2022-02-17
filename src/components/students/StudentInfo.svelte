<script>
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
            <span class="tag is-dark is-info is-medium">email</span>
            <span class="tag is-dark is-medium">{student.email}</span>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="tags has-addons">
            <span class="tag is-dark is-info is-medium">telephone</span>
            <span class="tag is-dark is-medium">{student.telephone}</span>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="tags has-addons">
            <span class="tag is-dark is-info is-medium">location</span>
            <span class="tag is-dark is-medium">{student.location}</span>
        </div>
    </div>
</ul> 