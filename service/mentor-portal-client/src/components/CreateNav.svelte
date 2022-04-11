<script context="module">
    // import components
    import CreateMentor from './CreateMentor.svelte';
    import CreateStudent from'./CreateStudent.svelte';
    // nav options
    export const navOptions = [
        { page: "Create Mentor", component: CreateMentor},
        { page: "Create Student", component: CreateStudent},
    ];
</script>