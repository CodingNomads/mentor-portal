import { get, writable } from "svelte/store";

export let userAuth = writable({
    "email": "testuser@email.com",
    "authToken": " Bearer {token}"
})

// const value = get(userAuth)

userAuth.subscribe(value => localStorage.setItem("userAuth", value))
