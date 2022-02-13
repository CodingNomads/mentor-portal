<script>
    import NavBar from "../components/NavBar.svelte";
    import { onMount } from "svelte";
    let student_list = "";

    onMount(async () => {
        const response = await fetch("http://localhost:8080/api/students", {
            mode: 'cors',
            credentials: "same-origin"
        })
        student_list = await response.json()
        console.log(student_list)
    })
</script>

<main>
    <div class="container">
        <NavBar />
        <div class="columns-is-centered">
            <div class="column m-3 is-5-tablet is-4-desktop is-5-widescreen">
                {#each student_list as student }
                <div class="card-body">
                    <h1 class="card-title"><strong>{student.lastName}, {student.firstName}</strong></h1>
                    <p>{student.roleCode}</p>
                    <p>{student.email}</p>
                    <p>{student.telephone}</p>
                    <hr>
                </div>
                {/each}
            </div>
        </div>
    </div>
</main>