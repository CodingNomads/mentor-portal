<script>
    export let studentList = [
        {
            id: 1,
            firstName: "Scott",
            lastName: "Ford",
            userName: "swford"
        },
        {
            id: 3,
            firstName: "Billy",
            lastName: "Bobb",
            userName: "bbobb"
        }
    ];
</script>

{#each studentList as student (student.id)}
<div class="column is-left">
    <button class="button is-info is-small is-light" href="/students/{student.id}">{student.firstName} {student.lastName} ({student.userName})</button>
    <button class="button is-small is-light">supportLog</button>
</div>
{/each}
