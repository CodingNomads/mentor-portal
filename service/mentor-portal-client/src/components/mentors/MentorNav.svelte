<script context="module">
    // import components
    import AssignedStudents from './AssignedStudents.svelte';
    import MentorInfo from './MentorInfo.svelte';
    import SupportLog from './SupportLog.svelte';
    import PasswordReset from './PasswordReset.svelte';
    // mentor nav options
    export const navOptions = [
        {page: "mentorInfo", component: MentorInfo},
        {page: "assignedStudents", component: AssignedStudents},
        {page: "supportLog", component: SupportLog},
        {page: "passwordReset", component: PasswordReset}
    ]
</script>