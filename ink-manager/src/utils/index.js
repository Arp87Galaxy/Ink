export function setAuthToken(token) {
    console.log("setAuthToken")
    window.localStorage.setItem("authToken",token)
}
export function deleteAuthToken() {
    console.log("deleteAuthToken")
    window.localStorage.removeItem("authToken")
}



