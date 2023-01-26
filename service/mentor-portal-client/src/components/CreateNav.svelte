<script context="module">
    // import components
    import CreateMentor from './CreateMentor.svelte';
    import CreateStudent from'./CreateStudent.svelte';

    // nav options
    export const navOptions = [
        { page: "createMentor", component: CreateMentor},
        { page: "createStudent", component: CreateStudent},
    ];
</script>