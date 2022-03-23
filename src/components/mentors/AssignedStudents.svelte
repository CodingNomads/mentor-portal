<script>
    export let mentor;
</script>

{#each mentor.assignedStudents as student (student.id)}
<div class="column is-left">
    <a class="button is-link is-small is-light" href="/students/{student.id}">{student.firstName} {student.lastName} ({student.forumUsername})</a>
</div>
{/each}
