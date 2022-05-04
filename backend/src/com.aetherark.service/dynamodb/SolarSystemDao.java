package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.SolarSystemNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolarSystemDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public SolarSystemDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public SolarSystem saveSolarSystem(SolarSystem solarSystem) {
        this.dynamoDBMapper.save(solarSystem);

        return solarSystem;
    }

    public SolarSystem getSolarSystem(String systemId) {
        SolarSystem solarSystem = this.dynamoDBMapper.load(SolarSystem.class, systemId);

        if(solarSystem == null) {
            throw new SolarSystemNotFoundException("Solar System Id: " + systemId + " not found.");
        }

        return solarSystem;
    }

    public SolarSystem deleteSolarSystem(SolarSystem solarSystem) {
        this.dynamoDBMapper.delete(solarSystem);

        return solarSystem;
    }

    public void deleteAllSolarSystemForUser(List<String> solarSystemIds) {
        List<SolarSystem> solarSystemList = new ArrayList<>();
        //iterate through the solarSystemIds
        for (String systemId: solarSystemIds){
            // set a system Id to a new system object
            SolarSystem systemToGet = new SolarSystem();
            systemToGet.setSystemId(systemId);
            // Add to the list
            solarSystemList.add(systemToGet);
        }
        dynamoDBMapper.batchDelete(solarSystemList);
    }

    public void deleteCelestialBodyFromAllSolarSystems(CelestialBody celestialBody) {
        Map<String, String> solarSystems = celestialBody.getSolarSystemNames();

        for (String systemId : solarSystems.keySet()) {
            SolarSystem system = getSolarSystem(systemId);
            system.getCelestialBodies().remove(celestialBody);
            system.getDistanceFromCenter().remove(celestialBody.getId());
            saveSolarSystem(system);
        }
    }

    public List<SolarSystem> getAllSolarSystemsForUser(String username) {
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.setUsername(username);

        DynamoDBQueryExpression<SolarSystem> queryExpression = new DynamoDBQueryExpression<SolarSystem>()
                .withHashKeyValues(solarSystem)
                .withConsistentRead(false)
                .withIndexName(SolarSystem.USERNAME_INDEX);

        return new ArrayList<>(dynamoDBMapper.query(SolarSystem.class, queryExpression));

    }
}
