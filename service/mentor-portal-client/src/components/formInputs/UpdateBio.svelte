<script>
    import { onMount } from "svelte";
    import Quill from "../../../node_modules/quill/dist/quill.js"
    import { authorizedApiPutCall } from "../../js/apiCalls.js";

    export let bio;
    export let mentorId;
    export let studentId;
    let quill;

    const authToken = sessionStorage.getItem("authToken")

    let mentorUrl = API_BASE_URL + `/api/mentors/${mentorId}`;
    let studentUrl = API_BASE_URL + `/api/students/${studentId}`;

    // quill rich text config
    onMount( () => {
        // quill toobar options
        const toolbarOptions = [
            [{header: [1, 2, 3, 4, 5, 6, false]}],
            ['bold', 'italic', 'underline', 'strike'],
            [{list: 'ordered'}, {list: 'bullet'}],
            ['blockquote', 'code-block'],
            ['link']
        ]
        // quill editor setup
        const updateContainer = document.getElementById("bioEditor");
        quill = new Quill(updateContainer, {
            modules: {
                toolbar: toolbarOptions
            },
            placeholder: "Format bio entry here.",
            theme: "snow"
        });
        const delta = quill.clipboard.convert(bio)
        quill.setContents(delta, 'silent')
    });

    async function submitUpdate() {
        // if student being updated
        if (typeof(studentId) === 'number' && typeof(mentorId) === 'undefined') {
            const body = JSON.stringify({
                'userId': studentId,
                'bio': quill.root.innerHTML
            })
            const response = await authorizedApiPutCall(authToken, body, studentUrl)
            if (response.status === 200) {
                alert(`Successfully updated bio.`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
        // if mentor being updated
        else if (typeof(mentorId) === 'number' && typeof(studentId) === 'undefined') {
            const body = JSON.stringify({
                'userId': mentorId,
                'bio': quill.root.innerHTML
            })
            const response = await authorizedApiPutCall(authToken, body, mentorUrl)
            if (response.status === 200) {
                alert(`Successfully updated bio`)
                window.location.reload()
            } else {
                alert(`Failed to update bio.`)
            }
        }
    };

</script>

<svelte:head>
	<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</svelte:head>

<form class="form" on:submit|preventDefault={submitUpdate}>
    <br>
    <div id="bioEditor" />
    <br>
    <button class="button is-small">Submit</button>
</form>