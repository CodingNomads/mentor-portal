<script>
	import Status from './routes/Status.svelte';
	import Main from './routes/Main.svelte';
	import Login from './routes/Login.svelte';
	import Students from './routes/Students.svelte'
	import Mentors from './routes/Mentors.svelte'

	import { Router, Link, Route } from 'svelte-routing';

	export let url = "";

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
			<Main />
		</Route>
	</div>

</Router>