<script context="module">
    // import components
    import AssignedStudents from './AssignedStudents.svelte';
    import MentorInfo from './MentorInfo.svelte';
    import SupportLog from './SupportLog.svelte';
    import PasswordReset from './PasswordReset.svelte';
    // mentor nav options
    export const navOptions = [
        {page: "Support Log", component: SupportLog},
        {page: "Mentor Info", component: MentorInfo},
        {page: "Assigned Students", component: AssignedStudents},
        {page: "Password Reset", component: PasswordReset}
    ]
</script>