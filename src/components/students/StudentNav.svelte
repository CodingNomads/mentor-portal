<script context="module">
    // import components
    import StudentInfo from "./StudentInfo.svelte";
    import SupportLog from "./SupportLog.svelte";
    // export navOptions
    export const navOptions = [
        {page: "studentInfo", component: StudentInfo},
        {page: "supportLog", component:SupportLog}
    ]
</script>
