export {InvalidAttributeException, InvalidAttributeChangeException, InvalidAttributeValueException, 
    UserNotFoundException, UserNameAlreadyExistsException, CelestialBodyNotFoundException,
SolarSystemNotFoundException, persistUserdata}

const InvalidAttributeException = "com.aetherark.service.exceptions.InvalidAttributeException";
const InvalidAttributeValueException = "com.aetherark.service.exceptions.InvalidAttributeValueException";
const InvalidAttributeChangeException = "com.aetherark.service.exceptions.InvalidAttributeChangeException";
const UserNotFoundException = "com.aetherark.service.exceptions.UserNotFoundException";
const UserNameAlreadyExistsException = "com.aetherark.service.exceptions.UserNameAlreadyExistsException";
const CelestialBodyNotFoundException = "com.aetherark.service.exceptions.CelestialBodyNotFoundException";
const SolarSystemNotFoundException = "com.aetherark.service.exceptions.SolarSystemNotFoundException";

function persistUserdata(res) {

    sessionStorage.setItem('username', res.name);
    sessionStorage.setItem('email', res.email);  
  
      console.log("sessionStorage",sessionStorage);
    
  }