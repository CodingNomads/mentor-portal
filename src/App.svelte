<script>
	import Status from './routes/Status.svelte';
	import Main from './routes/Main.svelte';
	import Login from './routes/Login.svelte';
	import Students from './routes/Students.svelte'
	import Mentors from './routes/Mentors.svelte'
	import auth from "./js/auth-service"


	import { onMount } from 'svelte';
	import { Router, Link, Route } from 'svelte-routing';
	import { isAuthenticated, user } from "./js/store";

	let auth0Client;

	export let url = "";

	onMount(async () => {
		auth0Client = await auth.createClient();

		isAuthenticated.set(await auth0Client.isAuthenticated());
		user.set(await auth0Client.getUser());
	});

	function login() {
		auth.loginWithPopup(auth0Client);
	}

	function logout() {
		auth.logout(auth0Client);
	}

</script>

<Router url ="{url}">



	<div>
		<Route path="/login">

			<Login />
		</Route>

		<Route path="/status">
			<Status /> 
		</Route>

		<Route path="/mentors">
			<Mentors />
		</Route>

		<Route path="/students">
			<Students />
		</Route>

		<Route path="/">
			{#if $isAuthenticated}
            <a class="" href="/#" on:click="{logout}">LogOut</a>

          {:else}
            <a class="" href="/#" on:click="{login}">Log In</a>
          {/if}


			<Main />
		</Route>
	</div>

</Router>