import {persistUserdata, UserNameAlreadyExistsException, InvalidAttributeValueException }
 from "/modules/utils.js";
window.onload = () => {
  const createBtn = document.getElementById("create");
  createBtn.addEventListener('click', login);
  console.log(sessionStorage); 
}

const login = async (evt) => {
  evt.preventDefault();
  console.log("Creating New User...");

  const username = document.getElementById("create-username").value;
  const email = document.getElementById("create-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  console.log(username);
  console.log(email);
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/`
  
  axios.post(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    console.log("errorType", res.data.errorType);
    const errorType = res.data.errorType;
    if (errorType == UserNameAlreadyExistsException ){
      alert(res.data.errorMessage);
      window.location.reload();
    };
    if (errorType == InvalidAttributeValueException ){
      alert(res.data.errorMessage);
      window.location.reload();
    };
    // persist the data to a session like variable
    persistUserdata(res.data.userModel);
    window.location.replace("/user/user-home.html");

  }).catch(function (error) {
    console.log("error",error);

  })
}
