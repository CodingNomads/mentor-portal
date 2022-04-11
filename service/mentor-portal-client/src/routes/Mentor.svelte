<script>
    import MentorDetail from "../components/mentors/MentorDetail.svelte";

    export let mentorId;
    console.log(mentorId)
</script>

<div>
    <MentorDetail mentorId={mentorId}/>
</div>