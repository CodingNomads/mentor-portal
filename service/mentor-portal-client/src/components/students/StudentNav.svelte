<script context="module">
    // import components
    import StudentInfo from "./StudentInfo.svelte";
    import SupportLog from "./SupportLog.svelte";
    import MentorshipTable from "./MentorshipTable.svelte";

    // export navOptions
    export const navOptions = [
        {page: "Support Log", component:SupportLog},
        {page: "Student Info", component: StudentInfo},
        {page: "Program Table", component: MentorshipTable},
    ];
</script>
