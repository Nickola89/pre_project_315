const userUrl = 'http://localhost:8080/api/currentUser';

async function getAuthUser() {
    let response = await fetch("/api/currentUser")
    return response.ok
        ? response.json()
        : null
}

/*export async function getAuthUsername() {
    return await getAuthUser()
        .then(authUser => authUser.username)
        .then(username => {
            return username === undefined ? null : username
        })
}*/
function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(auth => {
            console.log(auth);
            getInformationAboutUser(auth)
        }
    )

}

function getInformationAboutUser(auth) {
    let result = '';
    result =
        `<tr>
    <th><p>${auth.id}</p></th>
   
    <th><p>${auth.username}</p></th>
    <th><p>${auth.email}</p></th>
    <th><p>${auth.roles.map(r => r.name).join(' ').replaceAll('ROLE_', '')}</p></th>   
    </tr>`
    document.getElementById('userTableBody').innerHTML = result;
}

getUserPage();
