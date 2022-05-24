// GET REQUEST with valid token
export async function authorizedApiGetCall(authToken, url){
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
    if(response.status === 500){
        alert("The API may have broke, or you may have an expired token. Try to logout and log in.")
    }
    else if(response.status === 403){
        alert("You may not have valid permissions, or you might need to logout and get a new Token.")
        window.location.replace(CLIENT_BASE_URL + "/login")
    }
    // if valid authentication
    else if (response.status === 200) {
        const responseObject = await response.json()
        return responseObject
    }
};

// POST REQUEST with valid token and body
export async function authorizedApiPostCall(authToken, body, url){
    const headers = {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Authorization": authToken
    }
    const response = await fetch(url, {
        headers,
        body,
        method: "POST",
        mode: "cors",
        credentials: "same-origin"
    })
    // if invalid authentication
    if(response.status === 500){
        alert("The API may have broke, or you may have an expired token. Try to logout and log in.")
    }
    else if(response.status === 403){
        alert("You may not have valid permissions, or you might need to logout and get a new Token.")
    }
    else if(response.status === 200){
        const responseObject = await response.json()
        return responseObject
    }
}

// PUT REQUEST with valid token and body
export async function authorizedApiPutCall(authToken, body, url){
    const headers = {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Authorization": authToken
    }
    const response = await fetch(url, {
        headers,
        body,
        method: "PUT",
        mode: "cors",
        credentials: "same-origin"
    })
    // if invalid authentication
    if(response.status === 500){
        alert("The API may have broke, or you may have an expired token. Try to logout and log in.")
    }
    else if(response.status === 403){
        alert("You may not have valid permissions, or you might need to logout and get a new Token.")
        window.location.replace(CLIENT_BASE_URL + "/login")
    }
    else if(response.status === 200){
        return response
    }
}