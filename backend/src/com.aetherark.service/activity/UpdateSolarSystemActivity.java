package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.aetherark.service.exceptions.InvalidAttributeException;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.SolarSystemModel;
import com.aetherark.service.models.requests.UpdateSolarSystemRequest;
import com.aetherark.service.models.results.UpdateSolarSystemResult;
import com.aetherark.service.util.AetherArkServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class UpdateSolarSystemActivity implements RequestHandler<UpdateSolarSystemRequest, UpdateSolarSystemResult> {

    private final SolarSystemDao solarSystemDao;
    private final UserDao userDao;
    private final CelestialBodyDao celestialBodyDao;

    @Inject
    public UpdateSolarSystemActivity(SolarSystemDao solarSystemDao, UserDao userDao, CelestialBodyDao celestialBodyDao) {
        this.solarSystemDao = solarSystemDao;
        this.userDao = userDao;
        this.celestialBodyDao = celestialBodyDao;
    }

    @Override
    public UpdateSolarSystemResult handleRequest(final UpdateSolarSystemRequest updateSolarSystemRequest, Context context) {

        SolarSystem solarSystem;
        User user;
        CelestialBody celestialBodyToUpdateDistance;
        CelestialBody celestialBodyToAdd;
        CelestialBody celestialBodyToRemove;
        int defaultDistance = 10;


        try {
            user = userDao.getUser(updateSolarSystemRequest.getUsername());
            solarSystem = solarSystemDao.getSolarSystem(updateSolarSystemRequest.getSolarSystemId());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e);
        } catch (SolarSystemNotFoundException e) {
            throw new SolarSystemNotFoundException(e);
        }

        if (updateSolarSystemRequest.getNewSolarSystemName() != null
                && !updateSolarSystemRequest.getNewSolarSystemName().isBlank()) {
            if(!AetherArkServiceUtils.isValidString(updateSolarSystemRequest.getNewSolarSystemName())) {
                throw new InvalidAttributeException("New solar system name : " + updateSolarSystemRequest.getNewSolarSystemName() + " contains invalid characters");
            }
            solarSystem.setSystemName(updateSolarSystemRequest.getNewSolarSystemName());
            solarSystem.setCelestialBodies(celestialBodyDao.updateSolarSystemNameInCelestialBodies(solarSystem));
        }

        if(!solarSystem.getUsername().equals(user.getName())) {
            throw new UserNotFoundException("The provided solar system id: " + updateSolarSystemRequest.getSolarSystemId() + " does not belong " +
                    "to the user: " + updateSolarSystemRequest.getUsername());
        }

        //update distance block
        if (updateSolarSystemRequest.getCelestialBodyDistanceToUpdate() != null
                && !updateSolarSystemRequest.getCelestialBodyDistanceToUpdate().isBlank()) {
            try {
                celestialBodyToUpdateDistance = celestialBodyDao.getCelestialBody(updateSolarSystemRequest.getCelestialBodyDistanceToUpdate());
            } catch (CelestialBodyNotFoundException e) {
                throw new CelestialBodyNotFoundException("Celestial Body to Update distance does not exist");
            }

            if(!solarSystem.getCelestialBodies().contains(celestialBodyToUpdateDistance)) {
                throw new CelestialBodyNotFoundException("The provided celestial body to update: " + updateSolarSystemRequest.getCelestialBodyDistanceToUpdate() +
                        " does not belong to the solar system " + updateSolarSystemRequest.getSolarSystemId());
            }

            Integer newDistance = updateSolarSystemRequest.getNewDistanceFromCenter();
            if (newDistance == null || newDistance < 1) {
                throw new InvalidAttributeException("Updated Celestial Body Distance must be greater than or equal to 1");
            }
            solarSystem.getDistanceFromCenter().put(celestialBodyToUpdateDistance.getId(), newDistance);
        }

        //add body block
        if (updateSolarSystemRequest.getCelestialBodyIdToAddToSolarSystem() != null
        && !updateSolarSystemRequest.getCelestialBodyIdToAddToSolarSystem().isBlank()) {
            try {
                celestialBodyToAdd = celestialBodyDao.getCelestialBody(updateSolarSystemRequest.getCelestialBodyIdToAddToSolarSystem());

            } catch (CelestialBodyNotFoundException e) {
                throw new CelestialBodyNotFoundException("Celestial Body to Add To solar System does not exist");
            }
            //security check
            if(!user.getCelestialBodyIds().contains(celestialBodyToAdd.getId())) {
                throw new CelestialBodyNotFoundException("The provided celestial body to add " + updateSolarSystemRequest.getCelestialBodyIdToAddToSolarSystem() +
                        " does not belong to this user: " + user.getName());
            }
            CelestialBody updatedBody = celestialBodyDao.addSolarSystemNameToCelestialBody(celestialBodyToAdd.getId(), solarSystem);

            solarSystem.getCelestialBodies().add(updatedBody);
            solarSystem.getDistanceFromCenter().put(celestialBodyToAdd.getId(), defaultDistance);

        }

        //remove body block
        if (updateSolarSystemRequest.getCelestialBodyIdToRemoveFromSolarSystem() != null
        && !updateSolarSystemRequest.getCelestialBodyIdToRemoveFromSolarSystem().isBlank()) {
            try {
                celestialBodyToRemove = celestialBodyDao.getCelestialBody(updateSolarSystemRequest.getCelestialBodyIdToRemoveFromSolarSystem());
            } catch (CelestialBodyNotFoundException e) {
                throw new CelestialBodyNotFoundException("Celestial Body to Remove does not exist");
            }
            //security check
            if(!user.getCelestialBodyIds().contains(celestialBodyToRemove.getId())) {
                throw new CelestialBodyNotFoundException("The provided celestial body to remove " + updateSolarSystemRequest.getCelestialBodyIdToRemoveFromSolarSystem() +
                        " does not belong to this user: " + user.getName());
            }
            solarSystem.getCelestialBodies().remove(celestialBodyToRemove);
            solarSystem.getDistanceFromCenter().remove(celestialBodyToRemove.getId());

            celestialBodyDao.removeSolarSystemNameFromCelestialBody(celestialBodyToRemove.getId(), solarSystem);
        }

        //at this point- updated distance, added cbody, removed cbody, updated name,

        solarSystemDao.saveSolarSystem(solarSystem);

        SolarSystemModel solarSystemModel = new ModelConverter().toSolarSystemModel(solarSystem);

        return UpdateSolarSystemResult.builder()
                .withSolarSystemModel(solarSystemModel)
                .build();

    }

}