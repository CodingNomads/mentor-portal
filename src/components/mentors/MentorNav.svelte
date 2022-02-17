<script context="module">
    // import components
    import AssignedStudents from './AssignedStudents.svelte';
    import MentorInfo from './MentorInfo.svelte';
    // mentor nav options
    export const navOptions = [
        {page: "assignedStudents", component: AssignedStudents},
        {page: "mentorInfo", component: MentorInfo},
    ]
</script>