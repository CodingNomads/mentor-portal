<script>
	import { Router, Route, navigate } from 'svelte-routing';

	import Login from './routes/Login.svelte';
	import Students from './routes/Students.svelte'
	import Mentors from './routes/Mentors.svelte'
	import Main from './routes/Main.svelte';
	import Admin from './routes/Admin.svelte';
	import Status from './routes/Status.svelte';
	import Student from './routes/Student.svelte';
	import Mentor from './routes/Mentor.svelte';
	
	export let url = "";

	const isAuthenticated = sessionStorage.getItem("authToken");

	if(!isAuthenticated){
		navigate(CLIENT_BASE_URL + "/login", {replace: true})
	}; 
</script>

<Router url ="{url}">

	<div>
		<Route path="/login">
			<Login />
		</Route>

		<Route path="/">
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