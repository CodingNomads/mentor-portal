<script>
    async function logout(){
        // clear token to restrict access to webapp
        localStorage.clear();
        console.log("Logged out.")
        window.location.replace(CLIENT_BASE_URL + "/login")
    }
</script>

<button class="button" on:click={logout}>Logout</button>