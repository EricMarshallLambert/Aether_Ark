import {persistUserdata,InvalidAttributeValueException, UserNotFoundException }
 from "/modules/utils.js";
window.onload = () => {
  const updateBtn = document.getElementById("update");
  updateBtn.addEventListener('click', updateUser);
  const destroyBtn = document.getElementById("delete");
  destroyBtn.addEventListener('click', destroyUser);
  console.log(sessionStorage); 
}

const updateUser = async (evt) => {
  evt.preventDefault();
  console.log("Updating User Data...");

  const username = document.getElementById("settings-username").value;
  const email = document.getElementById("settings-email").value;
  const updatedEmail = document.getElementById("settings-updatedEmail").value;
  const userObj = {
    "email": email,
    "updatedEmail" : updatedEmail
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
  //loading spinner popup
  axios.put(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    console.log("errorType", res.data.errorType);
    const errorType = res.data.errorType;
    if (errorType == UserNotFoundException ){
      alert(res.data.errorMessage);
      window.location.reload();
      return;
    };
    if (errorType == InvalidAttributeValueException ){
      alert(res.data.errorMessage);
      window.location.reload();
      return;
    };
    
    persistUserdata(res.data.user);
    alert("Your account has been updated!");
    window.location.reload();
  }).catch(function (error) {
    console.log("error",error);
  })
}

const destroyUser = async (evt) => {
  evt.preventDefault();
console.log("Delete User Data...");

const username = document.getElementById("settings-username").value;
const email = document.getElementById("settings-email").value;

const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
axios.delete(url,  {params:{
  email : email
}})
  .then((res) => {
    console.log("response", res.data);
    console.log("errorType", res.data.errorType);
    const errorType = res.data.errorType;
    if (errorType == UserNotFoundException ){
      alert(res.data.errorMessage);
      window.location.reload();
      return;
    };
    if (errorType == InvalidAttributeValueException ){
      alert(res.data.errorMessage);
      window.location.reload();
      return;
    };
    // Take user data out of session storage
    sessionStorage.clear("username");
    sessionStorage.clear("email");
    alert("You DELETED your account!");
    // window.location.replace("/index.html");
  }).catch(function (error) {
    console.log("error",error);
  })
}



