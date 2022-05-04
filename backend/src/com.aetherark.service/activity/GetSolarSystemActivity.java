package com.aetherark.service.activity;

import com.aetherark.service.converters.ModelConverter;
import com.aetherark.service.dynamodb.SolarSystemDao;
import com.aetherark.service.dynamodb.UserDao;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.aetherark.service.exceptions.UserNotFoundException;
import com.aetherark.service.models.SolarSystemModel;
import com.aetherark.service.models.requests.GetSolarSystemRequest;
import com.aetherark.service.models.results.GetSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetSolarSystemActivity implements RequestHandler<GetSolarSystemRequest, GetSolarSystemResult> {
    private final SolarSystemDao solarSystemDao;
    private final UserDao userDao;


    @Inject
    public GetSolarSystemActivity(SolarSystemDao solarSystemDao, UserDao userDao) {
        this.solarSystemDao = solarSystemDao;
        this.userDao = userDao;
    }

    @Override
    public GetSolarSystemResult handleRequest(final GetSolarSystemRequest getSolarSystemRequest, Context context) {

        User user;
        SolarSystem solarSystem;


        try {
            user = userDao.getUser(getSolarSystemRequest.getUsername());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e);
        }

        ModelConverter modelConverter = new ModelConverter();

        //GSI BLOCK
        if (getSolarSystemRequest.isGetAll()) {
            // do stuff
            List<SolarSystem> solarSystems = new ArrayList<>();
            solarSystems = solarSystemDao.getAllSolarSystemsForUser(user.getName());


            List<SolarSystemModel> solarSystemModels = new ArrayList<>();


            for (SolarSystem system : solarSystems) {
                SolarSystemModel model = modelConverter.toSolarSystemModel(system);
                solarSystemModels.add(model);
            }


            return GetSolarSystemResult.builder().withSolarSystemModels(solarSystemModels).build();
        } else {


            try {
                solarSystem = solarSystemDao.getSolarSystem(getSolarSystemRequest.getSystemId());
            } catch (SolarSystemNotFoundException e) {
                throw new SolarSystemNotFoundException(e);
            }

            if (!solarSystem.getUsername().equals(user.getName())) {
                //todo throw security/system ownership exception exception
                throw new UserNotFoundException("This solar system is owned by a different user");
            }


            SolarSystemModel solarSystemModel = modelConverter.toSolarSystemModel(solarSystem);

            return GetSolarSystemResult.builder().
                    withSolarSystemModel(solarSystemModel)
                    .build();
        }
    }


}
