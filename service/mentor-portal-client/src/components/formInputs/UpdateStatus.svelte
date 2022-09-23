<script>
    import UserStatusDropdown from "./UserStatusDropdown.svelte";

    let userId;
    let value;
</script>

<form class="form">
    <UserStatusDropdown bind:value={value}/>
    <button class="button is-small">Submit</button>
</form>