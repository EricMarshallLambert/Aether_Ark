package com.aetherark.service.activity;

import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.UserTestHelper;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.models.requests.UpdateUserRequest;
import com.aetherark.service.models.results.UpdateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateUserActivityTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UpdateUserActivity updateUserActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void handleRequest_withEmailUsernameUpdatedEmail_returnsUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        String originalEmail = "www.expected@aetherark.com";
        String updatedEmail = "www.updated@email.com";

        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(userDao.getUser(expectedUsername)).thenReturn(expectedUser);

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(originalEmail)
                .withUpdatedEmail(updatedEmail)
                .build();
        //WHEN
        UpdateUserResult result = updateUserActivity.handleRequest(updateUserRequest, null);
        //THEN
        assertEquals(expectedUsername, result.getUser().getName());
        assertEquals(updatedEmail, result.getUser().getEmail());
    }

    @Test
    public void handleRequest_withDifferentUsernames_throwsInvalidAttributeException() {
        //GIVEN
        String username = "expectedName";
        String differentName = "DifferentName";
        String email = "www.expected@aetherark.com";
        String updatedEmail = "www.updated@email.com";
        User differentUser = UserTestHelper.expectedUser(differentName);
        when(userDao.getUser(username)).thenReturn(differentUser);

        UpdateUserRequest request = UpdateUserRequest.builder()
                .withUsername(username)
                .withEmail(email)
                .withUpdatedEmail(updatedEmail)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> updateUserActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_withIncorrectEmail_throwsInvalidAttributeException() {
        //GIVEN
        String username = "expectedName";
        String email = "www.expected@aetherark.com";
        String differentEmail = "www.different@aetherark.com";
        String updatedEmail = "www.updated@email.com";
        User user = UserTestHelper.expectedUserDiffEmail(username, differentEmail);
        when(userDao.getUser(username)).thenReturn(user);

        UpdateUserRequest request = UpdateUserRequest.builder()
                .withUsername(username)
                .withEmail(email)
                .withUpdatedEmail(updatedEmail)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> updateUserActivity.handleRequest(request, null));
    }
}