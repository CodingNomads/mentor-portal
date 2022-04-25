<script>
    export let mentor;
</script>

{#if mentor.assignedStudents.length > 0}

<ol>
{#each mentor.assignedStudents as student (student.id)}
<div class="column">
     <li><a class="is-link is-small" href="/students/{student.id}">{student.firstName} {student.lastName} ({student.forumUsername})</a></li>
</div>
{/each}
</ol> 

{:else}  
<div class="box">
    This mentor currently has no students
</div> 
{/if}
