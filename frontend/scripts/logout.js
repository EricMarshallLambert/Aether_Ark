window.onload = () =>{
    console.log("Logging out...");
    sessionStorage.clear();
    console.log(sessionStorage);
    window.location.replace("/index.html");
}
