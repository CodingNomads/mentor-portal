<script>
	import Status from './components/Status.svelte';
	import Main from './components/Main.svelte';
	import LoginForm from './components/LoginForm.svelte';

	import { Router, Link, Route } from 'svelte-routing';
	import { onMount, setContext } from 'svelte';
	import {
		key as userContextKey,
		initialValue as userContextInitialValue,
	} from './components/userContext';

	export let url = "";

	onMount(() => {
		setContext(userContextKey, userContextInitialValue);
	});

	const submit = ({email, password}) =>
        new Promise((resolve, reject) => {
			setTimeout(() => {
				setContext(userContextKey, {
					name: "Foo",
					lastName: "Bar",
					email: "foo@bar.com"
				});
				resolve();
			}, 1000);
		});
</script>

<Router url ="{url}">

	<div>
		<Route path="/status">
			<Status /> 
		</Route>

		<Route path="/test">
			<LoginForm submit={submit} />
		</Route>

		<Route path="/">
			<Main />
		</Route>
	</div>
</Router>