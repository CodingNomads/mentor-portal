
<script>
    let navbarToggle = false;
    const toggleNavbar = () => {
		navbarToggle = !navbarToggle
	}
</script>

<nav class="navbar is-white has-shadow">
    <!-- logo / brand -->
    <div class="navbar-brand">
      <a href="#/" class="navbar-item">
        <img src="assets/placeholder.com-logo1.png" alt="placeholder" style="max-height: 70px" class="py-2 px-2">
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
        <a class="navbar-item">Admin</a>
        <a class="navbar-item">Students</a>
        <a class="navbar-item">Mentors</a>
      </div>
    </div>
  </nav>