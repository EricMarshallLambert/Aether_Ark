package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a {@link User} to represent the model in DynamoDB.
 */
@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a UserDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the User table
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns the {@link User} corresponding to the specified username.
     *
     * @param name the username
     * @return the stored User, or if the given username is not found, will throw a UserNotFoundException.
     */
    public User getUser(String name) throws UserNotFoundException{
        User user = this.dynamoDBMapper.load(User.class, name);

        if (user == null) {
            throw new UserNotFoundException("User: " + name + " not found.");
        }
        return user;
    }

    /**
     * Saves the {@link User} to our database.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    public User saveUser(User user) {
        this.dynamoDBMapper.save(user);
        return user;
    }

    /**
     * Deletes the {@link User} from our database.
     *
     * @param user to be deleted
     * @return the deleted user
     */
    public User deleteUser(User user) {
        this.dynamoDBMapper.delete(user);
        return user;
    }

    /**
     * Adds a solarSystemId to our database.
     *
     * @param username The user to add the solarSystemId to.
     * @param solarSystemId The solarSystemId to be added
     * @return solarSystemId that was added to the database
     */
    public String addToUserSolarSystemId(String username, String solarSystemId) {
        User user = getUser(username);
        user.getSolarSystemIds().add(solarSystemId);
        saveUser(user);

        return solarSystemId;
    }

    /**
     * Deletes a solarSystemId from our database.
     *
     * @param username The user to delete the solarSystemId from.
     * @param solarSystemId The solarSystemId to be deleted
     * @return solarSystemId that was deleted from the database
     */
    public String removeFromUserSolarSystemId(String username, String solarSystemId) {
        User user = getUser(username);
        user.getSolarSystemIds().remove(solarSystemId);
        saveUser(user);

        return solarSystemId;
    }

    /**
     * Adds a CelestialBodyId to our database.
     *
     * @param username The user to add the CelestialBodyId to.
     * @param CelestialBodyId The CelestialBodyId to be added
     * @return CelestialBodyId that was added to the database
     */
    public String addToUserCelestialBodyId(String username, String CelestialBodyId) {
        User user = getUser(username);
        user.getCelestialBodyIds().add(CelestialBodyId);
        saveUser(user);

        return CelestialBodyId;
    }

    /**
     * Deletes a solarSystemId from our database.
     *
     * @param username The user to delete the CelestialBodyId from.
     * @param CelestialBodyId The CelestialBodyId to be deleted
     * @return CelestialBodyId that was deleted from the database
     */
    public String removeFromCelestialBodyId(String username, String CelestialBodyId) {
        User user = getUser(username);
        user.getCelestialBodyIds().remove(CelestialBodyId);
        saveUser(user);

        return CelestialBodyId;
    }

    public boolean usernameAlreadyExists(String username){
        User user = dynamoDBMapper.load(User.class, username);
        if (user != null){
            return true;
        }
        return false;
    }
}
