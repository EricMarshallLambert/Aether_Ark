package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTestHelper {

    public static User expectedUser(String name) {
        String expectedUsername = name;
        String expectedEmail = "www.expected@aetherark.com";
        String[] expectedSolarSystemIdsArray = new String[]{"1", "2", "3", "4"};
        String[] expectedCelestialBodyIdsArray = new String[]{"6", "7", "8", "9"};
        List<String> expectedSolarSystemIds = new ArrayList<>(Arrays.asList(expectedSolarSystemIdsArray));
        List<String> expectedCelestialBodyIds = new ArrayList<>(Arrays.asList(expectedCelestialBodyIdsArray));

        User expectedUser = new User();
        expectedUser.setName(expectedUsername);
        expectedUser.setEmail(expectedEmail);
        expectedUser.setSolarSystemIds(expectedSolarSystemIds);
        expectedUser.setCelestialBodyIds(expectedCelestialBodyIds);

        return expectedUser;
    }

    public static User expectedUserDiffEmail(String name, String differentEmail) {
        String expectedUsername = name;
        String expectedEmail = differentEmail;
        String[] expectedSolarSystemIdsArray = new String[]{"1", "2", "3", "4"};
        String[] expectedCelestialBodyIdsArray = new String[]{"6", "7", "8", "9"};
        List<String> expectedSolarSystemIds = new ArrayList<>(Arrays.asList(expectedSolarSystemIdsArray));
        List<String> expectedCelestialBodyIds = new ArrayList<>(Arrays.asList(expectedCelestialBodyIdsArray));

        User expectedUser = new User();
        expectedUser.setName(expectedUsername);
        expectedUser.setEmail(expectedEmail);
        expectedUser.setSolarSystemIds(expectedSolarSystemIds);
        expectedUser.setCelestialBodyIds(expectedCelestialBodyIds);

        return expectedUser;
    }
}
