<script>
    export let mentor;
</script>

{#if mentor.assignedStudents.length > 0}
{#each mentor.assignedStudents as student (student.id)}
<div class="column is-left">
    <a class="button is-link is-small is-light" href="/students/{student.id}">{student.firstName} {student.lastName} ({student.forumUsername})</a>
</div>
{/each} 
{:else}  
<div class="box">
    This mentor currently has no students
</div> 
{/if}
