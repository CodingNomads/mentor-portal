<script>
    let entry;
    let type;
    let duration;

    async function postSupportLog () {

    };
</script>

<form class="form-box" on:submit|preventDefault={postSupportLog}>
    <div class="row">
        <div class="columns">
            <div class="column is-one-fifth">
                <input type="text" placeholder="some text here">
                <input type="text" placeholder="more text here">
            </div>
            <div class="column is-four-fifths">
                <textarea name="" id="" cols="130" rows="7"></textarea>
            </div>
        </div>
    </div>
    <br>
    <div class="buttons">
        <button class="button is-success">Submit</button>
    </div>
</form>