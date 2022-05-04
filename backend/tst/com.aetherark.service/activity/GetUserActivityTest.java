package com.aetherark.service.activity;

import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.UserTestHelper;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.requests.GetUserRequest;
import com.aetherark.service.models.results.GetUserResult;
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

class GetUserActivityTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private GetUserActivity getUserActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void handleRequest_withUserFound_returnsUserModelInResult() {
        //GIVEN
        String expectedUsername = "expectedName";
        String expectedEmail = "www.expected@aetherark.com";
        String[] expectedSolarSystemIdsArray = new String[]{"1", "2", "3", "4"};
        String[] expectedCelestialBodyIdsArray = new String[]{"6", "7", "8", "9"};
        List<String> expectedSolarSystemIds = new ArrayList<>(Arrays.asList(expectedSolarSystemIdsArray));
        List<String> expectedCelestialBodyIds = new ArrayList<>(Arrays.asList(expectedCelestialBodyIdsArray));
        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(userDao.getUser(expectedUsername)).thenReturn(expectedUser);

        GetUserRequest request = GetUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(expectedEmail)
                .build();
        //WHEN
        GetUserResult result = getUserActivity.handleRequest(request, null);
        //THEN
        assertEquals(expectedUsername, result.getUser().getName());
        assertEquals(expectedEmail, result.getUser().getEmail());
        assertEquals(expectedSolarSystemIds, result.getUser().getSolarSystemIds());
        assertEquals(expectedCelestialBodyIds, result.getUser().getCelestialBodyIds());
    }

    @Test
    public void handleRequest_WithUserNotFound_throwsUserNotFoundException() {
        //GIVEN
        String username = "DifferentUser";
        String expectedEmail = "www.expected@aetherark.com";
        when(userDao.getUser(username)).thenThrow(new UserNotFoundException());

        GetUserRequest request = GetUserRequest.builder()
                .withUsername(username)
                .withEmail(expectedEmail)
                .build();
        //WHEN + THEN
        assertThrows(UserNotFoundException.class, ()-> getUserActivity.handleRequest(request, null));
        verify(userDao, times(1)).getUser(username);
    }

    @Test
    public void handleRequest_WithWrongEmail_throwsInvalidAttributeValueException() {
        //GIVEN
        String expectedUsername = "expectedName";
        String expectedEmail = "www.expected@aetherark.com";
        String differentEmail = "www.different@email.com";

        User expectedUserDifferentEmail = UserTestHelper.expectedUserDiffEmail(expectedUsername, differentEmail);
        when(userDao.getUser(expectedUsername)).thenReturn(expectedUserDifferentEmail);

        GetUserRequest request = GetUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(expectedEmail)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> getUserActivity.handleRequest(request, null));
        verify(userDao, times(1)).getUser(expectedUsername);
    }
}