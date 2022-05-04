package com.aetherark.service.activity;

import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.UserTestHelper;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.models.requests.DeleteUserRequest;
import com.aetherark.service.models.results.DeleteUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteUserActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private CelestialBodyDao celestialBodyDao;

    @Mock
    private SolarSystemDao solarSystemDao;

    @InjectMocks
    private DeleteUserActivity deleteUserActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void handleRequest_withUsernameEmail_returnsDeletedUser() {
        String expectedUsername = "expectedName";
        String expectedEmail = "www.expected@aetherark.com";
        String[] expectedSolarSystemIdsArray = new String[]{"1", "2", "3", "4"};
        String[] expectedCelestialBodyIdsArray = new String[]{"6", "7", "8", "9"};
        List<String> expectedSolarSystemIds = new ArrayList<>(Arrays.asList(expectedSolarSystemIdsArray));
        List<String> expectedCelestialBodyIds = new ArrayList<>(Arrays.asList(expectedCelestialBodyIdsArray));
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(userDao.getUser(expectedUsername)).thenReturn(expectedUser);

        DeleteUserRequest request = DeleteUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(expectedEmail)
                .build();
        //WHEN
        DeleteUserResult result = deleteUserActivity.handleRequest(request, null);
        //THEN
        assertEquals(expectedUsername, result.getUser().getName());
        assertEquals(expectedEmail, result.getUser().getEmail());
        assertEquals(expectedSolarSystemIds, result.getUser().getSolarSystemIds());
        assertEquals(expectedCelestialBodyIds, result.getUser().getCelestialBodyIds());
        verify(celestialBodyDao, times(1)).deleteCelestialBodiesList(expectedCelestialBodyIds);
        verify(solarSystemDao, times(1)).deleteAllSolarSystemForUser(expectedSolarSystemIds);
        verify(userDao, times(1)).deleteUser(expectedUser);
    }

    @Test
    public void handleRequest_withUsernameEmailNothingInList_returnsDeletedUser() {
        String expectedUsername = "expectedName";
        String expectedEmail = "www.expected@aetherark.com";

        //Set id Lists to empty lists
        List<String> expectedSolarSystemIds = new ArrayList<>();
        List<String> expectedCelestialBodyIds = new ArrayList<>();

        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        expectedUser.setCelestialBodyIds(expectedCelestialBodyIds);
        expectedUser.setSolarSystemIds(expectedSolarSystemIds);

        when(userDao.getUser(expectedUsername)).thenReturn(expectedUser);

        DeleteUserRequest request = DeleteUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(expectedEmail)
                .build();
        //WHEN
        DeleteUserResult result = deleteUserActivity.handleRequest(request, null);
        //THEN
        assertEquals(expectedUsername, result.getUser().getName());
        assertEquals(expectedEmail, result.getUser().getEmail());
        assertEquals(expectedSolarSystemIds, result.getUser().getSolarSystemIds());
        assertEquals(expectedCelestialBodyIds, result.getUser().getCelestialBodyIds());
        verify(celestialBodyDao, times(0)).deleteCelestialBodiesList(expectedCelestialBodyIds);
        verify(solarSystemDao, times(0)).deleteAllSolarSystemForUser(expectedSolarSystemIds);
        verify(userDao, times(1)).deleteUser(expectedUser);
    }

    @Test
    public void handleRequest_withDifferentUsernames_throwsInvalidAttributeException() {
        //GIVEN
        String username = "expectedName";
        String differentName = "DifferentName";
        String email = "www.expected@aetherark.com";
        User differentUser = UserTestHelper.expectedUser(differentName);
        when(userDao.getUser(username)).thenReturn(differentUser);

        DeleteUserRequest request = DeleteUserRequest.builder()
                .withUsername(username)
                .withEmail(email)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> deleteUserActivity.handleRequest(request, null));
    }
    @Test
    public void handleRequest_withIncorrectEmail_throwsInvalidAttributeException() {
        //GIVEN
        String username = "expectedName";
        String email = "www.expected@aetherark.com";
        String differentEmail = "www.different@aetherark.com";
        User userDiffEmail = UserTestHelper.expectedUserDiffEmail(username, differentEmail);
        when(userDao.getUser(username)).thenReturn(userDiffEmail);

        DeleteUserRequest request = DeleteUserRequest.builder()
                .withUsername(username)
                .withEmail(email)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> deleteUserActivity.handleRequest(request, null));
    }
}