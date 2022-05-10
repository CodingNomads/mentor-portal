// function to verify authorization for GET requests with valid token
export async function authorizedApiGetCall(authToken, url){
    console.log(authToken)
    const headers = {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Authorization": authToken
    }
    const response = await fetch(url, {
        headers,
        method: "GET",
        mode: "cors",
        credentials: "same-origin"
    })
    // // if invalid authentication
    if(response.status === 403){
        window.location.replace(CLIENT_BASE_URL + "/login")
    } 
    // if valid authentication
    else {
        const responseObject = await response.json()
        console.log(responseObject)
        return responseObject
    }
};