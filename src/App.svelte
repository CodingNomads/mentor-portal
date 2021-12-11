<script>
	import Status from './components/Status.svelte';
	import Main from './components/Main.svelte';

	import { Router, Link, Route } from 'svelte-routing'; 

	export let url = "";
</script>

<Router url ="{url}">

	<div>
		<Route path="/status">
			<Status /> 
		</Route>

		<Route path="/">
			<Main />
		</Route>
	</div>
</Router>