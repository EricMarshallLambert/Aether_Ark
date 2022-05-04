package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.UserModel;
import com.aetherark.service.models.requests.DeleteUserRequest;
import com.aetherark.service.models.results.DeleteUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the DeleteUserRequest.json for the bd-team-project-aether_ark's DeleteUser API.
 * <p>
 * This API allows the customer to delete their user info.
 */
public class DeleteUserActivity implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
        private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final CelestialBodyDao celestialBodyDao;
    private final SolarSystemDao solarSystemDao;

    /**
     * Instantiates a new DeleteUserRequest.json object.
     *
     * @param userDao userDao to access the userDao table.
     */
    @Inject
    public DeleteUserActivity(UserDao userDao, CelestialBodyDao celestialBodyDao, SolarSystemDao solarSystemDao) {
        this.userDao = userDao;
        this.celestialBodyDao = celestialBodyDao;
        this.solarSystemDao = solarSystemDao;
    }

    /**
     * This method handles the incoming request by retrieving the user from the database.
     * <p>
     * It then returns the user.
     * <p>
     *
     * @param deleteUserRequest request object containing the userDao request info
     * @return DeleteUserResult result object containing the API defined {@link UserModel}
     */
    public DeleteUserResult handleRequest(final DeleteUserRequest deleteUserRequest, Context context)
        throws UserNotFoundException {
        log.info("Received GetUserRequest {}", deleteUserRequest);

        String requestUsername = deleteUserRequest.getUsername();
        String requestEmail = deleteUserRequest.getEmail();

        User user;
        try {
            user = userDao.getUser(requestUsername);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Deleting " + e.getMessage());
        }

        //Security checks
        //Usernames must match
        if (!requestUsername.equals(user.getName())){
            throw new InvalidAttributeValueException("Usernames do not match");
        }

        //Original email must match before user is deleted
        if (!requestEmail.equals(user.getEmail())){
            throw new InvalidAttributeValueException("Please enter correct email");
        }

        //Delete all celestial bodies in the database for a user
        List<String> celestialBodyIdsList = user.getCelestialBodyIds();

        if (!celestialBodyIdsList.isEmpty()) {
            celestialBodyDao.deleteCelestialBodiesList(celestialBodyIdsList);
        }

        //Delete all solar systems in the database for a user
        List<String> solarSystemIdsList = user.getSolarSystemIds();

        if (!solarSystemIdsList.isEmpty()) {
            solarSystemDao.deleteAllSolarSystemForUser(solarSystemIdsList);
        }

        //Delete the user
        userDao.deleteUser(user);

        UserModel userModel = new ModelConverter().toUserModel(user);
        return DeleteUserResult.builder()
                .withUser(userModel)
                .build();
    }
}

