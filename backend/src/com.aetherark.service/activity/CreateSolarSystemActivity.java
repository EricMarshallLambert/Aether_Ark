package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.models.SolarSystemModel;
import com.aetherark.service.models.requests.CreateSolarSystemRequest;
import com.aetherark.service.models.results.CreateSolarSystemResult;
import com.aetherark.service.util.AetherArkServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateSolarSystemActivity implements RequestHandler<CreateSolarSystemRequest, CreateSolarSystemResult> {

    private final SolarSystemDao solarSystemDao;
    private final UserDao userDao;


    @Inject
    public CreateSolarSystemActivity(SolarSystemDao solarSystemDao, UserDao userDao) {
        this.solarSystemDao = solarSystemDao;
        this.userDao = userDao;
    }

    @Override
    public CreateSolarSystemResult handleRequest(final CreateSolarSystemRequest createSolarSystemRequest, Context context) {

        if(!AetherArkServiceUtils.isValidString(createSolarSystemRequest.getSystemName())) {
            throw new InvalidAttributeException("Solar System name contains invalid characters.");
        }

        /*
        if(userDao.userNameAlreadyExists(createSolarSystemRequest.getUsername())) {
           // throw new UsernameAlreadyExistsExcpetion;
        }
         */

        SolarSystem solarSystem = new SolarSystem();
        solarSystem.setSystemId(AetherArkServiceUtils.generateId());
        solarSystem.setSystemName(createSolarSystemRequest.getSystemName());
        solarSystem.setUsername(createSolarSystemRequest.getUsername());


        //no celestial bodies exist yet
        List<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        solarSystem.setCelestialBodies(celestialBodies);
        Map<String, Integer> distances = new HashMap<>();
        solarSystem.setDistanceFromCenter(distances);



        userDao.addToUserSolarSystemId(solarSystem.getUsername(), solarSystem.getSystemId());


        SolarSystemModel solarSystemModel = new ModelConverter().toSolarSystemModel(solarSystem);

        solarSystemDao.saveSolarSystem(solarSystem);

        return CreateSolarSystemResult.builder()
                .withSolarSystemModel(solarSystemModel)
                .build();
    }
}
