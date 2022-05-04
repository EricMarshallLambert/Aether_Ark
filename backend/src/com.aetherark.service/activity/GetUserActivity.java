package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.UserModel;
import com.aetherark.service.models.requests.GetUserRequest;
import com.aetherark.service.models.results.GetUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetUserRequest.json for the bd-team-project-aether_ark's GetUser API.
 *
 * This API allows the customer to get their user info.
 */public class GetUserActivity implements RequestHandler<GetUserRequest, GetUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new GetUserRequest.json object.
     *
     * @param userDao userDao to access the user table.
     */
    @Inject
    public GetUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by retrieving the user from the database.
     * <p>
     * It then returns the user.
     * <p>
     * If the user does not exist, this should throw a UserNotFoundException.
     *
     * @param getUserRequest request object containing the user ID
     * @return getUserResult result object containing the API defined {@link UserModel}
     */
    public GetUserResult handleRequest(final GetUserRequest getUserRequest, Context context)
            throws UserNotFoundException {
        log.info("Received GetUserRequest {}", getUserRequest);

        String requestUsername = getUserRequest.getUsername();
        String requestEmail = getUserRequest.getEmail();
//        if (requestEmail == null || requestUsername == null){
//            throw new InvalidAttributeValueException("");
//        }
        User user;
        try {
            user = userDao.getUser(requestUsername);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Sign in " + e.getMessage());
        }

        //Security Check emails must match
        if (!requestEmail.equals(user.getEmail())){
            throw new InvalidAttributeValueException("You didn't say the magic word!");
        }

        UserModel userModel = new ModelConverter().toUserModel(user);
        return GetUserResult.builder()
                .withUser(userModel)
                .build();
    }
}
