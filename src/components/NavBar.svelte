<script>
  import { Link } from 'svelte-routing';
  let navbarToggle = false;
  const toggleNavbar = () => {
		navbarToggle = !navbarToggle
	}
</script>

<nav class="navbar is-white has-shadow">
    <!-- logo / brand -->
    <div class="navbar-brand">
      <a href="http://localhost:5000/" class="navbar-item">
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
        <a class="navbar-item" href="http://localhost:5000/admin">Admin</a>
        <a class="navbar-item" href="http://localhost:5000/students">Students</a>
        <a class="navbar-item" href="http://localhost:5000/mentors">Mentors</a>
      </div>
    </div>
  </nav>