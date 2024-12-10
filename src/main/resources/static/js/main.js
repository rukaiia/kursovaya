function saveUser(user) {
    let userAsJson = JSON.stringify(user)
    localStorage.setItem('user', userAsJson)
}

function readUser() {
    let userAsJson = localStorage.getItem('user')
    return JSON.parse(userAsJson)
}

const baseUrl = 'http://localhost:8089'

function makeHeaders() {
    let headers = new Headers()
    let user = readUser()
    headers.set('Content-Type', 'application/json')
    if (user) {
        headers.set('Authorization', 'Basic ' + btoa(user.username + ":" + user.password))
    }
    return headers
}

const requestSettings = {
    method: 'GET',
    headers: makeHeaders()
}

async function makeRequest(url, options) {
    let settings = options || requestSettings
    let response = await fetch(url, settings)

    if (response.ok) {
        return await response.json()
    } else {
        let error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
}

function updateOptions(options) {
    let update = {...options};
    update.mode = 'cors';
    update.headers = makeHeaders()
    return update;
}