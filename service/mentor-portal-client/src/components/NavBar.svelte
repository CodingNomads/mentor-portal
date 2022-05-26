<script>
  import LogoutButton from "./LogoutButton.svelte";

  const base_url = CLIENT_BASE_URL
  const loginUrl = CLIENT_BASE_URL + "/login"
  const isAuthenticated = localStorage.getItem("authToken")
  const isAdmin = localStorage.getItem("isAdmin")
  let navbarToggle = false;
  
  const toggleNavbar = () => {
		navbarToggle = !navbarToggle
	}
</script>

<nav class="navbar is-white has-shadow">
    <!-- logo / brand -->
    <div class="navbar-brand">
      <a href="{base_url}" class="navbar-item">
        <img src="/assets/small_new_cropped_codingnomads_logo.png" alt="placeholder" style="max-height: 70px" class="py-2 px-2">
      </a>
      <a class="navbar-burger" id="burger" on:click={toggleNavbar}>
        <span></span>
        <span></span>
        <span></span>
      </a>
    </div>
  
    <div class="navbar-menu" class:is-active={navbarToggle} id="nav-links">
      <!-- right links -->
      <div class="navbar-end">
        <!-- create student/mentor restricted to admin -->
        {#if isAdmin === "true"}
        <a class="navbar-item" href="{base_url + '/admin'}">Admin</a>
        {/if}
        <a class="navbar-item" href="{base_url + '/students'}">Students</a>
        <a class="navbar-item" href="{base_url + '/mentors'}">Mentors</a>
        <!-- logout button if authenticated -->
        {#if isAuthenticated}
          <a class="navbar-item" href="{loginUrl}"><LogoutButton /></a>
        {/if}
      </div>
    </div>
  </nav>