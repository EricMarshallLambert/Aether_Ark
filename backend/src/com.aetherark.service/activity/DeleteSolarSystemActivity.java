package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.SolarSystemModel;
import com.aetherark.service.models.requests.DeleteSolarSystemRequest;
import com.aetherark.service.models.results.DeleteSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class DeleteSolarSystemActivity implements RequestHandler<DeleteSolarSystemRequest, DeleteSolarSystemResult> {

    private final SolarSystemDao solarSystemDao;
    private final UserDao userDao;
    private final CelestialBodyDao celestialBodyDao;

    @Inject

    public DeleteSolarSystemActivity(SolarSystemDao solarSystemDao, UserDao userDao, CelestialBodyDao celestialBodyDao) {
        this.solarSystemDao = solarSystemDao;
        this.userDao = userDao;
        this.celestialBodyDao = celestialBodyDao;
    }

    @Override
    public DeleteSolarSystemResult handleRequest(final DeleteSolarSystemRequest deleteSolarSystemRequest, Context context) {

        User user;
        SolarSystem solarSystem;

        try {
            user = userDao.getUser(deleteSolarSystemRequest.getUsername());
            solarSystem = solarSystemDao.getSolarSystem(deleteSolarSystemRequest.getSystemId());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e);
        } catch (SolarSystemNotFoundException e) {
            throw new SolarSystemNotFoundException(e);
        }

        //security check
        if(!solarSystem.getUsername().equals(user.getName())) {
            throw new UserNotFoundException("User : " + deleteSolarSystemRequest.getUsername() +  " cannot delete solar system : " + deleteSolarSystemRequest.getSystemId() +
                    " because it isn't theirs");
        }


        celestialBodyDao.deleteSolarSystemFromAllCelestialBodies(solarSystem);
        userDao.removeFromUserSolarSystemId(user.getName(), solarSystem.getSystemId());
        solarSystemDao.deleteSolarSystem(solarSystem);

        ModelConverter modelConverter = new ModelConverter();
        SolarSystemModel solarSystemModel = modelConverter.toSolarSystemModel(solarSystem);

        return DeleteSolarSystemResult.builder()
                .withSolarSystemModel(solarSystemModel)
                .build();

    }



}
