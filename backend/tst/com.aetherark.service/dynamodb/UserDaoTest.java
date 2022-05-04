package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getUser_withUsername_returnUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        User user = userDao.getUser(expectedUsername);
        //THEN
        assertEquals(expectedUser, user);
    }

    @Test
    public void getUser_withUsernameNotFound_throwsUserNotFoundException() {
        //This test seems a bit redundant
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUser)).thenThrow(new UserNotFoundException());
        //WHEN + // THEN
        assertThrows(UserNotFoundException.class, () -> userDao.getUser(expectedUsername));
    }

    @Test
    public void saveUser_withUserFound_returnsUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        //WHEN
        User user = userDao.saveUser(expectedUser);
        //THEN
        assertEquals(expectedUser, user);
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void deleteUser_withUsername_returnsUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        //WHEN
        User user = userDao.deleteUser(expectedUser);
        //THEN
        assertEquals(expectedUser, user);
        verify(dynamoDBMapper, times(1)).delete(expectedUser);
    }

    @Test
    public void addToUserSolarSystemId_withUsernameAndSolarSystemId_returnsSolarSystemId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String solarSystemId = "5";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.addToUserSolarSystemId(expectedUsername, solarSystemId);
        //THEN
        assertEquals(5, expectedUser.getSolarSystemIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void removeFromUserSolarSystemId_withUsernameAndSolarSystemId_returnsSolarSystemId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String solarSystemId = "4";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.removeFromUserSolarSystemId(expectedUsername,solarSystemId);
        //THEN
        assertEquals(3, expectedUser.getSolarSystemIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void addToUserCelestialBodyId_withUsernameAndCelestialBodyId_returnsCelestialBodyId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String CelestialBodyId = "10";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
            userDao.addToUserCelestialBodyId(expectedUsername,CelestialBodyId);
        //THEN
        assertEquals(5, expectedUser.getCelestialBodyIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

    @Test
    public void removeFromUserCelestialBodyId_withUsernameAndCelestialBodyId_returnsCelestialBodyId() {
        //GIVEN
        String expectedUsername = "expectedName";
        String CelestialBodyId = "9";
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(dynamoDBMapper.load(User.class, expectedUsername)).thenReturn(expectedUser);
        //WHEN
        userDao.removeFromCelestialBodyId(expectedUsername,CelestialBodyId);
        //THEN
        assertEquals(3, expectedUser.getCelestialBodyIds().size());
        verify(dynamoDBMapper, times(1)).save(expectedUser);
    }

}
