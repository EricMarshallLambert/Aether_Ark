import {persistUserdata, InvalidAttributeValueException, UserNotFoundException }
 from "/modules/utils.js";
let cnt = 0;
const solarSystemList = document.querySelector("#user-solar-list");
const celestialBodyList = document.querySelector("#user-body-list");
const info = document.getElementById("info-list");
//set up canvas
var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
c.width = c.width;
c.height = c.height;


window.onclick = (evt) => {
  console.log(evt);
  const id = evt.target.text;
  console.log(id)
  if (evt.target.class = "celestialBodyId"){
   getCelestialBody(id);

      // body.displayInfo();
  }
  if (evt.target.class === "solar-system-id"){
    // getSolarSystem(id);
    //make new celestialBody object
    //draw
    //info
  }

}

window.onload = (evt) => {
  console.log(sessionStorage);
  cnt = 0;
  const username = sessionStorage.getItem("username");
  const email = sessionStorage.getItem("email");
  
  evt.preventDefault();
  console.log("Getting User Data...");

const userUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
axios.get(userUrl, {params:{
    email : email
}})
  .then((res) => {
  console.log("response", res.data);
  populateCelestialBodies(res.data.user.celestialBodyIds);
  populateSolarSystems(res.data.user.solarSystemIds);
 
}).catch(function (error) {
  // handle error
  console.log("error",error);
})
}

//////////////get solarSystem//////////////////
 function getSolarSystem(solarSystemId){
console.log("Getting solar system...");
const username = sessionStorage.getItem("username");
const solarUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarSystem/${solarSystemId}`

axios.get(solarUrl)
  .then((res) => {
  console.log("response", res.data);
  return res.data;

}).catch(function (error) {
  // handle error
  console.log("error",error);
})
}
//////////////get celestialBodys//////////////////
function getCelestialBody(celestialBodyId){
  console.log("Getting celestial body...");
  const username = sessionStorage.getItem("username");
  const bodyUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/celestialBody/${celestialBodyId}`
  
  axios.get(bodyUrl)
    .then((res) => {
    console.log("response", res.data.celestialBody);
    const cBd = res.data.celestialBody;
    const body = new CelestialBody(cBd.bodyName, cBd.diameter, 0,
      cBd.mass, cBd.composition, cBd.solarSystemNames);

    body.draw();
    body.displayInfo(cBd);
  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
  }

function populateSolarSystems(solarSystemData) {
  
  for (let solarSystem of solarSystemData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(solarSystem);
    //we want to have a link populate our form instead
    a.setAttribute('href', `#`);
    a.setAttribute('class', "solar-system-id")

    a.appendChild(text);
    li.appendChild(a);
    solarSystemList.appendChild(li);
  }
}

function populateCelestialBodies(celestialBodyData) {
  
  for (let celestialBody of celestialBodyData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(celestialBody);
    //we want to have a link populate our form instead
    a.setAttribute('href', `#`);
    a.setAttribute('class', `celestialBodyId`);
    

    a.appendChild(text);
    li.appendChild(a);
    celestialBodyList.appendChild(li);
  }
}


class CelestialBody {
  constructor(name, diameter, distanceFronCenter, mass, composition, solarSystemNames){
      this.name = name;
      this.diameter = diameter;
      this.distanceFronCenter = distanceFronCenter;
      this.mass = mass
      this.composition = composition;
      this.solarSystemNames = solarSystemNames;
  }

  
  draw () {
    const x = (c.width/2) + this.distanceFronCenter;
    const y = (c.height/2) + this.distanceFronCenter;
      ctx.beginPath();
      ctx.arc(x, y, this.diameter/2, 0, Math.PI * 2, false);
      ctx.fillStyle = getCompositionColor(this.composition);
      ctx.fill();
    // var img = "/images/png-clipart-meteor.png";
    // ctx.drawImage(img, 10, 10);
  }

  update() {
      // this.draw();
      // this.x += this.velocity.x;
      // this.y += this.velocity.y; 
  }

  displayInfo(celestialBodyData){
    //displayInfo for this body
    // let li = document.createElement("li");
    // let p = document.createElement("p");
    // let text = document.createTextNode();
    // //we want to have a link populate our form instead
    // p.setAttribute('class', `display-info-item`);
    // p.appendChild(text);
    // li.appendChild(a);
    // info.appendChild(li);

    var name = celestialBodyData.name;
    var composition = celestialBodyData.composition;
    var diameter = celestialBodyData.diameter;
    var mass = celestialBodyData.mass;
    var solarSystemNames = celestialBodyData.solarSystemNames;

    var list = [ + name, composition, diameter, mass, solarSystemNames]
   
    for (let item of list){
      let li = document.createElement("li");
      li.innerText = item;
      info.appendChild(li);
    }
  }


}

function getCompositionColor(composition){
    // switch (composition){
    //   case 'EARTH_PLANET' : 
    //   return 'green'
    //   case 'ICE_GIANT'
    // }
    return 'red';
}

class SolarSystem {
  constructor(solarSystemName, username, solarSystemId, celestialBodies, distancesFromCenter){
    this.solarSystemName = solarSystemName;
    this.username = username;
    this.solarSystemId = solarSystemId;
    this.celestialBodies = celestialBodies;
    this.distancesFromCenter = distancesFromCenter;
  }

  draw(){
    //to be implemented
    //iterate through the list of celestial bodies make new one
    //then draw the new one
    //repeat
  }

  update(){
    //to be implemented
  }

  displayInfo(){
    //to be implemented
  }
  

}

function animate(){
  requestAnimationFrame()
}