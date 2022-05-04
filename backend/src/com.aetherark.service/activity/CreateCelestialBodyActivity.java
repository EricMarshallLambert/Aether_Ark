package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.requests.CreateCelestialBodyRequest;
import com.aetherark.service.models.results.CreateCelestialBodyResult;
import com.aetherark.service.util.AetherArkServiceUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class CreateCelestialBodyActivity
        implements RequestHandler<CreateCelestialBodyRequest, CreateCelestialBodyResult> {
    private final CelestialBodyDao celestialBodyDao;
    private final UserDao userDao;

    @Inject
    public CreateCelestialBodyActivity(CelestialBodyDao celestialBodydao, UserDao userDao) {
        this.celestialBodyDao = celestialBodydao;
        this.userDao = userDao;
    }

    @Override
    public CreateCelestialBodyResult handleRequest(
            final CreateCelestialBodyRequest createBodyRequest, Context context) {

//        TODO: add logging?

        try {
            User user = userDao.getUser(createBodyRequest.getUsername());
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException(exception.getMessage());
        }

        if (!AetherArkServiceUtils.isValidString(createBodyRequest.getName())) {
            throw new InvalidAttributeException(String.format(
                    "The provided name: %s contains invalid characters", createBodyRequest.getName()));
        }

        CelestialBody newCelestialBody = new CelestialBody();
        newCelestialBody.setId(AetherArkServiceUtils.generateId()); // is ID definitely unique?
        newCelestialBody.setName(createBodyRequest.getName());
        newCelestialBody.setUsername(createBodyRequest.getUsername());
        newCelestialBody.setDiameter(createBodyRequest.getDiameter());
        newCelestialBody.setMass(createBodyRequest.getMass());
        newCelestialBody.setComposition(createBodyRequest.getComposition());
        // Not a member of any SolarSystems
        Map<String, String> systemMap = new HashMap<>();
        newCelestialBody.setSolarSystemNames(systemMap);

        celestialBodyDao.saveCelestialBody(newCelestialBody);
        userDao.addToUserCelestialBodyId(newCelestialBody.getUsername(), newCelestialBody.getId());

        CelestialBodyModel celestialBodyModel = new ModelConverter().toCelestialBodyModel(newCelestialBody);
        return CreateCelestialBodyResult.builder()
                .withCelestialBody(celestialBodyModel)
                .build();
    }
}
