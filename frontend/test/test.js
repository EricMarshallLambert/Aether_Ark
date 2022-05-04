window.onload = () => {
    const loginBtn = document.getElementById("login");
    loginBtn.addEventListener('click', login);
    const getBtn = document.getElementById("GET");
    getBtn.addEventListener('click', getUser );
    const postBtn = document.getElementById("POST");
    postBtn.addEventListener('click', post );
    const putBtn = document.getElementById("PUT");
    putBtn.addEventListener('click', put);     
    const deleteBtn = document.getElementById("DELETE");
    deleteBtn.addEventListener('click', destroyUser);  
    console.log(sessionStorage);  
}
//login
const login = async (evt) => {
  evt.preventDefault();
  console.log("Login....");
  
  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/login/`
  populateRequest(username);
  populateRequest(email);
  
  axios.post(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    console.log(userObj);
    populateResponse(res.data.user.name);
    populateResponse(res.data.user.email);
    
    //persist the data to a session like variable
    persistUserdata(res);

  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
}

//get user
const getUser = async (evt) => {
    evt.preventDefault();

    //for use with onsubmit
//   console.log("Getting User Data...");

//   // first we get the form element
//   const form = document.getElementById('login-username-form');

//   // then we pass the form element to the FormData object 
//   const formData = new FormData(evt.target);

console.log('formData: ', formData.get('login-username'));


  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;

  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
  populateRequest(username);
  populateRequest(email); 

  axios.get(url, {params:{
      email : email
  }})
    .then((res) => {
    console.log("response", res.data);
    populateResponse(res.data.user.name);
    populateResponse(res.data.user.email);
    populateResponse(res.data.user.solarSystemIds);
    populateResponse(res.data.user.celestialBodyIds);

  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
}

//new user
const post = async (evt) => {
    evt.preventDefault();
  console.log("Creating New User Data...");

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user`
  populateRequest(username);
  populateRequest(email);

  axios.post(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    populateResponse(res.data.user.name);
    populateResponse(res.data.user.email);
    populateResponse(res.data.user.solarSystemIds);
    populateResponse(res.data.user.celestialBodyIds);

  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
}

const put = async (evt) => {
    evt.preventDefault();
    console.log("Updating User Data...");
  
    const username = document.getElementById("login-username").value;
    const email = document.getElementById("login-email").value;
    const updatedEmail = document.getElementById("login-updateEmail").value;
    const userObj = {
      "email": email,
      "updatedEmail" : updatedEmail
    };
    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
    populateRequest(username);
    populateRequest(email);
  
    axios.put(url, userObj)
      .then((res) => {
      console.log("response", res.data);
      populateResponse(res.data.user.name);
      populateResponse(res.data.user.email);
      populateResponse(res.data.user.solarSystemIds);
      populateResponse(res.data.user.celestialBodyIds);
 
    }).catch(function (error) {
      // handle error
      console.log("error",error);
    })
}

const destroyUser = async (evt) => {
    evt.preventDefault();
  console.log("Delete User Data...");

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;

  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
  populateRequest(username);
  populateRequest(email);

  axios.delete(url,  {params:{
    email : email
}})
    .then((res) => {
    console.log("response", res.data);
    populateResponse(res.data.user.name);
    populateResponse(res.data.user.email);

  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
}


function persistUserdata(res) {

  sessionStorage.setItem('username', res.data.user.name);
  sessionStorage.setItem('email', res.data.user.email);  

    console.log("sessionStorage",sessionStorage);
  
}

function populateRequest(data) {
    
    let ul = document.getElementById("request-data");    
      let li = document.createElement("li");
      let p = document.createElement("p");
      let text = document.createTextNode(data);

      p.appendChild(text);
      li.appendChild(p);
      ul.appendChild(li);
    
  }

  
function populateResponse(data) {
    
    let ul = document.getElementById("response-data");    
      let li = document.createElement("li");
      let p = document.createElement("p");
      let text = document.createTextNode(data);

      p.appendChild(text);
      li.appendChild(p);
      ul.appendChild(li);
    
  }
