package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.requests.DeleteCelestialBodyRequest;
import com.aetherark.service.models.results.DeleteCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteCelestialBodyActivity
        implements RequestHandler<DeleteCelestialBodyRequest, DeleteCelestialBodyResult> {

    private final CelestialBodyDao celestialBodyDao;
    private final SolarSystemDao solarSystemDao;
    private final UserDao userDao;

    @Inject
    public DeleteCelestialBodyActivity(CelestialBodyDao celestialBodyDao, SolarSystemDao solarSystemDao,
                                       UserDao userDao) {
        this.celestialBodyDao = celestialBodyDao;
        this.solarSystemDao = solarSystemDao;
        this.userDao = userDao;
    }

    @Override
    public DeleteCelestialBodyResult handleRequest(
            final DeleteCelestialBodyRequest deleteBodyRequest, Context context) {

        CelestialBody bodyToDelete;
        try {
            bodyToDelete = celestialBodyDao.getCelestialBody(deleteBodyRequest.getCelestialBodyId());
        } catch (CelestialBodyNotFoundException exception) {
            throw new CelestialBodyNotFoundException(exception.getMessage());
        }

        if (!bodyToDelete.getUsername().equals(deleteBodyRequest.getUsername())) {
            throw new InvalidAttributeException(String.format(
                    "This celestial body does not belong to the user: %s", deleteBodyRequest.getUsername()));
        }

        celestialBodyDao.deleteCelestialBody(bodyToDelete);
        solarSystemDao.deleteCelestialBodyFromAllSolarSystems(bodyToDelete);
        userDao.removeFromCelestialBodyId(deleteBodyRequest.getUsername(), deleteBodyRequest.getCelestialBodyId());


        CelestialBodyModel celestialBodyModel = new ModelConverter().toCelestialBodyModel(bodyToDelete);

        return DeleteCelestialBodyResult.builder()
                .withCelestialBody(celestialBodyModel)
                .build();
    }
}
