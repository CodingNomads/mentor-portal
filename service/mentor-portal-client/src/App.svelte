<script>
	import { Router, Link, Route } from 'svelte-routing';
	import { isAuthenticated, user } from "./js/store";

	import Login from './routes/Login.svelte';
	import Students from './routes/Students.svelte'
	import Mentors from './routes/Mentors.svelte'
	import auth from "./js/auth-service"

	export let url = "";

	// onMount(async () => {
	// 	auth0Client = await auth.createClient();

	// 	isAuthenticated.set(await auth0Client.isAuthenticated());
	// 	user.set(await auth0Client.getUser());
	// });

	// function login() {
	// 	auth.loginWithPopup(auth0Client);
	// }

	// function logout() {
	// 	auth.logout(auth0Client);
	// }

	import Main from './routes/Main.svelte';
	import Admin from './routes/Admin.svelte';
	import Status from './routes/Status.svelte';
	import Student from './routes/Student.svelte';
	import Mentor from './routes/Mentor.svelte';

	// import auth from "./js/auth-service"

	// let auth0Client;
	// onMount(async () => {
	// 	auth0Client = await auth.createClient();
	// 	isAuthenticated.set(await auth0Client.isAuthenticated());
	// 	user.set(await auth0Client.getUser());
	// });
	// function login() {
	// 	auth.loginWithPopup(auth0Client);
	// }
	// function logout() {
	// 	auth.logout(auth0Client);
	// }
</script>

<Router url ="{url}">



	<div>
		<Route path="/login">

			<Login />
		</Route>

		<Route path="/">
			<!-- {#if $isAuthenticated}
            	<a class="" href="/#" on:click="{logout}">LogOut</a>
          	{:else}
            	<a class="" href="/#" on:click="{login}">Log In</a>
          	{/if} -->
			<Main />
		</Route>

		<Route path="/admin">
			<Admin />
		</Route>

		<Route path="/status">
			<Status /> 
		</Route>

		<Route path="/mentors">
			<Mentors />
		</Route>

		<Route path="/mentors/:mentorId" component="{Mentor}" let:params>
			<Mentor mentorId="{params.mentorId}"/>
		</Route>

		<Route path="/students">
			<Students />
		</Route>

		<Route path="/students/:studentId" component="{Student}" let:params>
			<Student studentId="{params.studentId}" />
		</Route>
	</div>

</Router>