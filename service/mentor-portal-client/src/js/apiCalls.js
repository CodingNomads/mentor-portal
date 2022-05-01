export function executeAuthorizedApiCall(path){
    Headers.add("Authorization", `Bearer ${token}`)
    response = fetch(API_BASE_URL + path)
    // if valid authentication
    if(response.status === 200){
        return response.body
    } 
    // if invalid authentication
    else if(response.status === 403){
        window.location.replace(CLIENT_BASE_URL + "/login")
    }
};