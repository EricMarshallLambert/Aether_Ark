package com.aetherark.service.activity;

import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.UserTestHelper;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeValueException;
import com.aetherark.service.exceptions.UserNameAlreadyExistsException;
import com.aetherark.service.models.requests.CreateUserRequest;
import com.aetherark.service.models.results.CreateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CreateUserActivityTest {
    @Mock
    private UserDao userDao;
    
    @InjectMocks
    private CreateUserActivity createUserActivity;
    
    @BeforeEach
    public void setUp() {
        initMocks(this);
    }
    
    @Test
    public void handleRequest_withUsernameAndEmail_returnsNewUser() {
        //GIVEN
        String expectedUsername = "expectedName";
        String expectedEmail = "www.expected@aetherark.com";

        User expectedUser = UserTestHelper.expectedUser(expectedUsername);
        when(userDao.saveUser(expectedUser)).thenReturn(expectedUser);

        CreateUserRequest request = CreateUserRequest.builder()
                .withUsername(expectedUsername)
                .withEmail(expectedEmail)
                .build();
        //WHEN
        CreateUserResult result = createUserActivity.handleRequest(request, null);
        //THEN
        assertEquals(expectedUsername, result.getUserModel().getName());
        assertEquals(expectedEmail, result.getUserModel().getEmail());
        assertEquals(0, result.getUserModel().getSolarSystemIds().size());
        assertEquals(0, result.getUserModel().getCelestialBodyIds().size());
    }

    @Test
    public void handleRequest_withUsernameInvalidCharacterAndEmail_throwsInvalidAttibuteException() {
        //GIVEN
        String usernameInvalidCharacter = "expected\'Name";
        String expectedEmail = "www.expected@aetherark.com";

        CreateUserRequest request = CreateUserRequest.builder()
                .withUsername(usernameInvalidCharacter)
                .withEmail(expectedEmail)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> createUserActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_withUsernameAndEmailInvalidCharacter_throwsInvalidAttibuteException() {
        //GIVEN
        String username = "expectedName";
        String emailInvalidCharacter = "www.expec\'ted@aetherark.com";

        CreateUserRequest request = CreateUserRequest.builder()
                .withUsername(username)
                .withEmail(emailInvalidCharacter)
                .build();
        //WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, ()-> createUserActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_withUsernameAndEmail_UserNameAlreadyExistsException() {
        //GIVEN
        String username = "expectedName";
        String email = "www.expected@aetherark.com";

        when(userDao.usernameAlreadyExists(username)).thenReturn(true);

        CreateUserRequest request = CreateUserRequest.builder()
                .withUsername(username)
                .withEmail(email)
                .build();
        //WHEN + THEN
        assertThrows(UserNameAlreadyExistsException.class, ()-> createUserActivity.handleRequest(request, null));
    }
}
