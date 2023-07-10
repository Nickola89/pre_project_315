const userUrl = 'http://localhost:8080/api/currentUser';


function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {
    console.log(user)
    let result = '';
    result =
        `<tr>
    <th><p>${user.id}</p></th>
    <th><p>${user.username}</p></th>
    <th><p>${user.email}</p></th>
    <th><p>${user.roles.map(r => r.name).join(' ').replaceAll('ROLE_', '')}</p></th>   
    </tr>`
    document.getElementById('userTableBody').innerHTML = result;
}

getUserPage();
