const planetTable = document.querySelector("#planet-table");
const urlParams = new URLSearchParams(window.location.search);
//const username = urlParams.get('username');
let thead = planetTable.createTHead();
let tbody = planetTable.createTBody();
let row = thead.insertRow();
let th = document.createElement("th");
let text = document.createTextNode("BodyId");
th.appendChild(text);
row.appendChild(th);

let th1 = document.createElement("th");
let text1 = document.createTextNode("Name");
th1.appendChild(text1);
row.appendChild(th1);

let th2 = document.createElement("th");
let text2 = document.createTextNode("Diameter");
th2.appendChild(text2);
row.appendChild(th2);

let th3 = document.createElement("th");
let text3 = document.createTextNode("Mass");
th3.appendChild(text3);
row.appendChild(th3);

let th4 = document.createElement("th");
let text4 = document.createTextNode("Composition");
th4.appendChild(text4);
row.appendChild(th4);

let th5 = document.createElement("th");
let text5 = document.createTextNode("Home Solar Systems");
th5.appendChild(text5);
row.appendChild(th5);


window.onload = async function (evt) {
    evt.preventDefault();
    console.log("Getting All Solar Systems...");
    // const username = document.querySelector("#user-name").value;
    // const email = document.querySelector("#password").value;
    const username = sessionStorage.username;
    const email = sessionStorage.email;

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
            //   console.log("response", res.data.user);
            let currentUserName = res.data.user.name;
            let currentUserEmail = res.data.user.email;
            let userSolarSystemIdsList = res.data.user.solarSystemIds;
            let userCelestialBodyIdsList = res.data.user.celestialBodyIds;


            let map = new Map();



            for (i = 0; i < userCelestialBodyIdsList.length; i++) {
                let celestialBodyId = userCelestialBodyIdsList[i];
                const systemUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/celestialBody/${celestialBodyId}`;
                axios.get(systemUrl).then((res1) => {
                    // console.log("celestial body response", res1.data);
                    let cbody = res1.data;
                    //  map.set(i, cbody);




                    populateCelestialBodies(res1);



                })


            }





        })

    //get solar system






    function populateCelestialBodies(celestialBodyData) {
        // let thead = planetTable.createTHead();
        // let tbody = planetTable.createTBody();
        // let row = thead.insertRow();
        console.log("method log", celestialBodyData.data.celestialBody);
        // console.log("bodyid", celestialBodyData.data.celestialBody.bodyId);
        // console.log("name", celestialBodyData.data.celestialBody.name);
        // console.log("diameter", celestialBodyData.data.celestialBody.diameter);
        // console.log("mass", celestialBodyData.data.celestialBody.mass);
        // console.log("composotion", celestialBodyData.data.celestialBody.composition);
        // console.log("solarSystemNames", celestialBodyData.data.celestialBody.solarSystemNames);







        let headers = ["bodyid", 'name', 'diameter', 'mass', 'composition', 'solarSystemNames'];


        // for (let key in celestialBodyData.data.celestialBody) {
        //     let th = document.createElement("th");
        //     let text = document.createTextNode(key);
        //     th.appendChild(text);
        //     row.appendChild(th);

        // }

        for (key in celestialBodyData.data) {
            let row = tbody.insertRow();
            let body = row.insertCell();
            body.innerHTML = celestialBodyData.data.celestialBody.bodyId;
            let name = row.insertCell();
            name.innerHTML = celestialBodyData.data.celestialBody.name;
            let diameter = row.insertCell();
            diameter.innerHTML = celestialBodyData.data.celestialBody.diameter;
            let mass = row.insertCell();
            mass.innerHTML = celestialBodyData.data.celestialBody.mass;
            let composition = row.insertCell();
            composition.innerHTML = celestialBodyData.data.celestialBody.composition;

            let SolarSystems = row.insertCell();
            SolarSystems.innerHTML = celestialBodyData.data.celestialBody.solarSystemNames;
            console.log("key data", celestialBodyData.data.celestialBody.solarSystemNames);

            // let text = document.createTextNode(celestialBodyData.data.celestialBody[key]);
            // cell.appendChild(text);
        }

        // for (key in celestialBodyData.data) {
        //     let row = tbody.insertRow();
        //     let name = row.insertCell();
        //     name.innerHTML = celestialBodyData.data.celestialBody.name;
        //     console.log(name);
        //     // let text = document.createTextNode(celestialBodyData.data.celestialBody[key]);
        //     // cell.appendChild(text);
        // }









        // for (let planet of celestialBodyData.data.celestialBody) {
        //     let row = tbody.insertRow();
        //     for (key in planet) {
        //         let cell = row.insertCell();
        //         let text = document.createTextNode(system[key]);
        //         cell.appendChild(text);
        //     }
        // }




    }
}


