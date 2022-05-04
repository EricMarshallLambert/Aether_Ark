package com.aetherark.service.activity;


import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.CelestialBodyDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.requests.GetCelestialBodyRequest;
import com.aetherark.service.models.results.GetCelestialBodyResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetCelestialBodyActivity implements RequestHandler<GetCelestialBodyRequest, GetCelestialBodyResult> {
    private final CelestialBodyDao celestialBodyDao;
    private final UserDao userDao;

    @Inject
    public GetCelestialBodyActivity(CelestialBodyDao celestialBodyDao, UserDao userDao) {
        this.celestialBodyDao = celestialBodyDao;
        this.userDao = userDao;
    }


    @Override
    public GetCelestialBodyResult handleRequest(
            final GetCelestialBodyRequest getCelestialBodyRequest, Context context) {
//        TODO: add logging functionality?

        User user;
        CelestialBody celestialBody;

        try {
            user = userDao.getUser(getCelestialBodyRequest.getUsername());
            celestialBody = celestialBodyDao.getCelestialBody(getCelestialBodyRequest.getCelestialBodyId());
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException(exception.getMessage());
        } catch (CelestialBodyNotFoundException exception) {
            throw new CelestialBodyNotFoundException(exception.getMessage());
        }

        if (!celestialBody.getUsername().equals(user.getName())) {
            throw new CelestialBodyNotFoundException("This celestial body is owned by a different user");
        }

        CelestialBodyModel celestialBodyModel = new ModelConverter().toCelestialBodyModel(celestialBody);

        return GetCelestialBodyResult.builder()
                .withCelestialBody(celestialBodyModel)
                .build();
    }
}
