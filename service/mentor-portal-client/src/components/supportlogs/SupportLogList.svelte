<script>
    import { onMount } from "svelte";
    import NavBar from "../NavBar.svelte";
    import UpdateLog from "../formInputs/UpdateLog.svelte";
    import { authorizedApiGetCall } from "../../js/apiCalls";

    // admin variables
    const authToken = sessionStorage.getItem("authToken")
    const isAdmin = sessionStorage.getItem("isAdmin")
    const mentorId = sessionStorage.getItem("mentorId")

    // other variables
    let editLogId;
    export let student;
    export let mentor;
    export let studentDictionary = {};
    export let mentorDictionary = {};
    export let studentMap = [];
    export let mentorMap = [];
    export let supportLogs = [];
    export let studentList = [];
    export let mentorList = [];

    // handler functions
    function editLog(supportLogId) {
        editLogId = supportLogId;
        return editLogId
    };
    const cancelEdit = () => {
        editLogId = 0;
        return editLogId
    };

    onMount(async () => {
        // urls for api calls
        const url = API_BASE_URL + "/api/supportlogs"
        const studentListUrl = API_BASE_URL + "/api/students"
        const mentorListUrl = API_BASE_URL + "/api/mentors"
        // api calls
        supportLogs = await authorizedApiGetCall(authToken, url)
        studentList = await authorizedApiGetCall(authToken, studentListUrl)
        mentorList = await authorizedApiGetCall(authToken, mentorListUrl)
        // sorting logs by date
        supportLogs = supportLogs.sort(function(a,b) {
            return new Date(b.logDate) - new Date(a.logDate)
        })
        // get student id and fullname
        studentMap = supportLogs.map(entry => {
            const studentId = entry.studentId;
            student = studentList.filter(student => student.id === studentId)[0]
            if (typeof student != "undefined"){
                studentDictionary[student.id] = student.firstName + " " + student.lastName
            }
        })
        // get mentor id and fullname
        mentorMap = supportLogs.map(entry => {
            const mentorId = entry.mentorId;
            mentor = mentorList.filter(mentor => mentor.id === mentorId)[0];
            if (typeof mentor != "undefined"){
                mentorDictionary[mentor.id] = mentor.firstName + " " + mentor.lastName
            }
        })
    })
</script>

<div class='container'>
    <NavBar />
    <br>
    <h1 class="title">Support Logs</h1>
    <h3 class="subtitle">Ordered by most recent</h3>
    <table class="table is-fullwidth">
        <tbody>
            {#each supportLogs as entry (entry.id)}
                <!-- catch flagged logs -->
                {#if entry.flag === true}
                    <tr>
                        <div class="notification is-danger is-light">
                            <div class="columns">
                                <!-- meta data -->
                                <div class="column is-one-fifth">
                                    <p><em>{entry.logDate.split("T")[0]}</em></p>
                                    <p><em>{entry.type}</em></p>
                                    <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                                    <p><em>{studentDictionary[entry.studentId] || mentorDictionary[entry.studentId]}</em></p>
                                </div>
                                <!-- support log topic body -->
                                <div class="is-offset-1 column is-three-fifths">
                                    <div class="content">
                                        {@html entry.log}
                                    </div>
                                    {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                        <br>
                                        <br>
                                        <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                    {/if}
                                </div>
                            </div>
                            <!-- triggered edit form -->
                            {#if editLogId === entry.id}
                                <UpdateLog log={entry.log} userId={entry.studentId} supportLogId={entry.id} />
                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                            {/if}
                        </div>
                    </tr>
                <!-- non-flagged logs -->
                {:else}
                    <tr>
                        <div class="box">
                            <div class="columns">
                                <!-- meta data -->
                                <div class="column is-one-fifth">
                                    <p><em>{entry.logDate.split("T")[0]}</em></p>
                                    <p><em>{entry.type}</em></p>
                                    <p><em>{entry.mentorFirstName} {entry.mentorLastName}</em></p>
                                    <p><em>{studentDictionary[entry.studentId] || mentorDictionary[entry.studentId]}</em></p>
                                </div>
                                <!-- support log topic body -->
                                <div class="is-offset-1 column is-three-fifths">
                                    <div class="content">
                                        {@html entry.log}
                                    </div>
                                    {#if isAdmin === "true" || entry.mentorId === parseInt(mentorId)}
                                        <br>
                                        <br>
                                        <button class="button is-small" on:click={() => editLog(entry.id)}>Edit</button>
                                    {/if}
                                </div>
                            </div>
                            {#if editLogId === entry.id}
                                <UpdateLog log={entry.log} userId={entry.studentId} supportLogId={entry.id} />
                                <button class="button is-small" on:click={cancelEdit}>Cancel</button>
                            {/if}
                        </div>
                    </tr>
                {/if}
                <br>
            {/each}
        </tbody>
    </table>
</div>
<footer class="footer">
    <!-- spacer -->
</footer>
