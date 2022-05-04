package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNameAlreadyExistsException;
import com.aetherark.service.models.UserModel;
import com.aetherark.service.models.requests.CreateUserRequest;
import com.aetherark.service.models.results.CreateUserResult;
import com.aetherark.service.util.AetherArkServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Implementation of the CreateUserRequest.json for the bd-team-project-aether_ark's CreateUser API.
 * <p>
 * This API allows the customer to register an account. It creates a new user with no solar systems
 * or celestial bodies
 */
public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
        private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new CreateUserRequest.json object.
     *
     * @param userDao userDao to access the user table.
     */
    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by creating a new user in the database.
     * <p>
     * It then returns the user.
     * <p>
     *
     * @param createUserRequest request object containing the username and email
     * @return CreateUserResult result object containing the API defined {@link UserModel}
     */
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        log.info("Received CreateUserRequest {}", createUserRequest);

        String username = createUserRequest.getUsername();
        String email = createUserRequest.getEmail();

        boolean isValidUsername = AetherArkServiceUtils.isValidString(username);
        if (!isValidUsername){
            throw new InvalidAttributeValueException("Username contains invalid characters: " + username);
        }

        boolean isValidEmail = AetherArkServiceUtils.isValidString(email);
        if (!isValidEmail){
            throw new InvalidAttributeValueException("Email contains invalid characters: " + email);
        }

        //check to see if the username already exists
        boolean usernameDoesExist = userDao.usernameAlreadyExists(username);
        if (usernameDoesExist){
            throw new UserNameAlreadyExistsException("This username already exists");
        }

        //create a user with an empty list of both Ids
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setSolarSystemIds(new ArrayList<>());
        user.setCelestialBodyIds(new ArrayList<>());

        userDao.saveUser(user);

        UserModel userModel = new ModelConverter().toUserModel(user);
        return CreateUserResult.builder()
                .withUser(userModel)
                .build();
    }
}
