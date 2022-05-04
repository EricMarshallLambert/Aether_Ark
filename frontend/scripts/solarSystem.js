// import { persistUserdata }
//     from "/modules/utils.js";
const solarSystemTable = document.querySelector("#solar-system-table");
const urlParams = new URLSearchParams(window.location.search);
//const username = urlParams.get('username');




window.onload = async function (evt) {
    evt.preventDefault();
    console.log("Getting All Solar Systems...");
    // const username = document.querySelector("#user-name").value;
    // const email = document.querySelector("#password").value;
    const username = sessionStorage.username;
    const email = sessionStorage.email;
    console.log("session storage", sessionStorage);

    const userObj = {
        "email": email
    }

    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`;
    axios.get(url, {
        params: {
            email: email
        }
    })
        .then((res) => {
            console.log("response", res.data.user);
            let currentUserName = res.data.user.name;
            let currentUserEmail = res.data.user.email;
            let userSolarSystemIdsList = res.data.user.solarSystemIds;
            const firstSolarSystemId = userSolarSystemIdsList[0];
            let userCelestialBodyIdsList = res.data.user.celestialBodyIds;
            const systemUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarSystem/${firstSolarSystemId}`;

            if (userSolarSystemIdsList.length > 0) {
                axios.get(systemUrl, { params: { getAll: true } })
                    .then((res) => {

                        populateSolarSystems(res.data);


                    })


            } else {
                console.log('This user has no solar systems');
            }

            // populateSolarSystems(userSolarSystemIdsList);

            const createBtn = document.getElementById("create");
            createBtn.addEventListener('click', createSolar);

            const destroyBtn = document.getElementById("Delete");
            destroyBtn.addEventListener('click', destroySolarSystem);

        })








    function populateSolarSystems(solarSystemData) {
        // console.log("id", solarSystemData.solarSystemModels[0].systemId);
        // console.log("name", solarSystemData.solarSystemModels[0].systemName);
        // console.log("bodies", solarSystemData.solarSystemModels[0].celestialBodies);
        // console.log("distance", solarSystemData.solarSystemModels[0].distanceFromCenter);
        // console.log("username", solarSystemData.solarSystemModels[0].username);
        // console.log("array length", solarSystemData.solarSystemModels.length);



        let thead = solarSystemTable.createTHead();
        let tbody = solarSystemTable.createTBody();
        let row = thead.insertRow();



        let headers = ["Solar System Ids", 'System Name', 'Celestial Bodies', 'Distance From Center', 'Username'];


        for (let key in solarSystemData.solarSystemModels[0]) {
            let th = document.createElement("th");
            let text = document.createTextNode(key);
            th.appendChild(text);
            row.appendChild(th);
        }

        for (let system of solarSystemData.solarSystemModels) {
            let row = tbody.insertRow();
            for (key in system) {
                let cell = row.insertCell();
                let text = document.createTextNode(system[key]);
                cell.appendChild(text);
            }
        }


    }
}

const createSolar = async (evt) => {
    evt.preventDefault();
    console.log("Creating New Solar System...");

    const username = sessionStorage.username;
    const solarSystemName = document.getElementById("solarSystem-name").value;
    const solarObj = {
        "username": username,
        "systemName": solarSystemName
    };
    console.log(username);
    console.log(solarSystemName);
    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarSystem/`

    axios.post(url, solarObj)
        .then((res) => {
            console.log("response", res.data);
            console.log("errorType", res.data.errorType);
            const errorType = res.data.errorType;
            window.location.reload();

            // persist the data to a session like variable
            //persistUserdata(res.data.userModel);
            //window.location.replace("/user/user-home.html");

        })

    // .catch(function (error) {
    //   console.log("error",error);

    //})
}

const destroySolarSystem = async (evt) => {
    evt.preventDefault();
    console.log("Delete Solar System...");

    const username = sessionStorage.username;
    const systemId = document.getElementById("delete-solarSystem-id").value;

    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarSystem/`
    axios.delete(url, {
        params: {
            systemId: systemId
        }
    })
        .then((res) => {
            console.log("response", res.data);
            console.log("errorType", res.data.errorType);
            const errorType = res.data.errorType;

        })
}


