package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.requests.UpdateCelestialBodyRequest;
import com.aetherark.service.models.results.UpdateCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class UpdateCelestialBodyActivity
        implements RequestHandler<UpdateCelestialBodyRequest, UpdateCelestialBodyResult> {

    private final CelestialBodyDao celestialBodyDao;
    private final SolarSystemDao solarSystemDao;

    @Inject
    UpdateCelestialBodyActivity(CelestialBodyDao celestialBodyDao, SolarSystemDao solarSystemDao) {
        this.celestialBodyDao = celestialBodyDao;
        this.solarSystemDao = solarSystemDao;
    }

    @Override
    public UpdateCelestialBodyResult handleRequest(
            final UpdateCelestialBodyRequest updateBodyRequest, Context context) {

        CelestialBody bodyToChange;
        try {
            bodyToChange = celestialBodyDao.getCelestialBody(updateBodyRequest.getCelestialBodyId());
        } catch (CelestialBodyNotFoundException exception) {
            throw new CelestialBodyNotFoundException(exception.getMessage());
        }

        if (!bodyToChange.getUsername().equals(updateBodyRequest.getUsername())) {
            throw new InvalidAttributeException(String.format(
                    "This celestial body does not belong to this user: %s", updateBodyRequest.getUsername()));
        }


        //Keep this for reference later
        CelestialBody bodyToRemove = celestialBodyDao.getCelestialBody(updateBodyRequest.getCelestialBodyId());

        bodyToChange.setName(updateBodyRequest.getName());
        bodyToChange.setDiameter(updateBodyRequest.getDiameter());
        bodyToChange.setMass(updateBodyRequest.getMass());
        bodyToChange.setComposition(updateBodyRequest.getComposition());
        celestialBodyDao.saveCelestialBody(bodyToChange);

        // Also save that newly edited CelestialBody to all SolarSystems as well.
        Map<String, String> solarSystems = bodyToChange.getSolarSystemNames();
        for (String systemId : solarSystems.keySet()) {
            SolarSystem system = solarSystemDao.getSolarSystem(systemId);
            List<CelestialBody> systemBodies = system.getCelestialBodies();
            int index = systemBodies.indexOf(bodyToRemove);
            systemBodies.remove(index);
            systemBodies.add(index, bodyToChange);
            system.setCelestialBodies(systemBodies);
            solarSystemDao.saveSolarSystem(system);
        }

        CelestialBodyModel celestialBodyModel = new ModelConverter().toCelestialBodyModel(bodyToChange);

        return UpdateCelestialBodyResult.builder()
                .withCelestialBody(celestialBodyModel)
                .build();
    }
}
