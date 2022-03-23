<script>
    import StudentDetail from "../components/students/StudentDetail.svelte";
    export let studentId;
    console.log(studentId)
</script>

<div>
    <StudentDetail studentId={studentId} />
</div>