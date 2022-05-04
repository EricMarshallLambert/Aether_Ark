import {persistUserdata, InvalidAttributeValueException, UserNotFoundException }
 from "/modules/utils.js";
window.onload = () => {
  const loginBtn = document.getElementById("login");
  loginBtn.addEventListener('click', login);
  // const spin = document.getElementsByClassName("spin");
  console.log(sessionStorage); 
}

const login = async (evt) => {
  evt.preventDefault();
  console.log("Logging in...");
  // displaySpinner();

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  console.log(username);
  console.log(email);
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/login/`
  //loading spinner popup
  axios.post(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    console.log("errorType", res.data.errorType);
    const errorType = res.data.errorType;
    if (errorType == UserNotFoundException ){
      alert(res.data.errorMessage);
      window.location.reload();
    };
    if (errorType == InvalidAttributeValueException ){
      alert(res.data.errorMessage);
      window.location.reload();
    };
    // persist the data to a session like variable

    persistUserdata(res.data.user);
    window.location.replace("/user/user-home.html");

  }).catch(function (error) {
    console.log("error",error);

  })
}

// function displaySpinner() {
// document.getElementsByClassName("spin").visibility = visible;
  
// } 

