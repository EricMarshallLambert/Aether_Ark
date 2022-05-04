package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.UserModel;
import com.aetherark.service.models.requests.UpdateUserRequest;
import com.aetherark.service.models.results.UpdateUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateUserRequest.json for the bd-team-project-aether_ark's UpdateUser API.
 * <p>
 * This API allows the customer to update their user info.
 */
public class UpdateUserActivity implements RequestHandler<UpdateUserRequest, UpdateUserResult> {
        private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new UpdateUserRequest.json object.
     *
     * @param userDao userDao to access the user table.
     */
    @Inject
    public UpdateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by retrieving the user from the database.
     * <p>
     * It then returns the user result.
     * <p>
     *
     * @param updateUserRequest request object containing the user ID
     * @return UpdateUserResult result object containing the API defined {@link UserModel}
     */
    public UpdateUserResult handleRequest(final UpdateUserRequest updateUserRequest, Context context)
        throws UserNotFoundException {
        log.info("Received updateUserRequest {} ", updateUserRequest);

        String requestUsername = updateUserRequest.getUsername();
        String requestEmail = updateUserRequest.getEmail();
        String requestUpdatedEmail = updateUserRequest.getUpdatedEmail();

        User user;
        try {
            user = userDao.getUser(requestUsername);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Updating " + e.getMessage());
        }

        //Security checks
        //Usernames must match
        if (!requestUsername.equals(user.getName())){
            throw new InvalidAttributeValueException("Usernames do not match");
        }

        //Original email must match before email change
        if (!requestEmail.equals(user.getEmail())){
            throw new InvalidAttributeValueException("Please enter correct email");
        }

        user.setEmail(requestUpdatedEmail);

        userDao.saveUser(user);

        UserModel userModel = new ModelConverter().toUserModel(user);
        return UpdateUserResult.builder()
                .withUser(userModel)
                .build();
    }
}

