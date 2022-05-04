package com.aetherark.service.dynamodb;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.exceptions.CelestialBodyNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class CelestialBodyDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a CelestialBodyDao Object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the CelestialBody table
     */
    @Inject
    public CelestialBodyDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link CelestialBody} corresponding to the provided id.
     *
     * @param id the CelestialBody ID
     * @return the Celestial Body, or throws {@link CelestialBodyNotFoundException} if given id is not found
     */
    public CelestialBody getCelestialBody(String id) {
        CelestialBody body = this.dynamoDbMapper.load(CelestialBody.class, id);

        if (body == null) {
            throw new CelestialBodyNotFoundException("Could not find planet with id " + id);
        }
        return body;
    }

    /**
     * Saves the provided {@link CelestialBody} object to the DynamoDB table.
     *
     * @param body the Celestial Body that will be saved
     * @return the Celestial Body that was saved
     */
    public CelestialBody saveCelestialBody(CelestialBody body) {
        dynamoDbMapper.save(body);
        return body;
    }

    /**
     * Deletes the {@link CelestialBody} from the DynamoDB table.
     *
     * @param body the Celestial Body to be deleted
     * @return the deleted Celestial Body
     */
    public CelestialBody deleteCelestialBody(CelestialBody body) {
        this.dynamoDbMapper.delete(body);
        return body;
    }

    /**
     * Adds the provided {@link SolarSystem} to the list of SolarSystems in this {@link CelestialBody} object.
     *
     * @param bodyId the ID of the Celestial Body which has been added to the Solar System
     * @param solarSystem the Solar System that now contains the Celestial Body
     * @return the updated Celestial Body object that can now be saved to the {@link SolarSystem}
     */
    public CelestialBody addSolarSystemNameToCelestialBody(String bodyId, SolarSystem solarSystem) {
        CelestialBody body = getCelestialBody(bodyId);

        Map<String, String> systemNames = body.getSolarSystemNames();
        systemNames.put(solarSystem.getSystemId(), solarSystem.getSystemName());
        body.setSolarSystemNames(systemNames);

        return saveCelestialBody(body);
    }

    /**
     * Removes the provided {@link SolarSystem} from the list of SolarSystems of which this {@link CelestialBody} is a member.
     *
     * @param bodyId the ID of the Celestial Body which has been removed from the Solar System
     * @param solarSystem the Solar System that no longer contains the Celestial Body
     * @return the updated Celestial Body object
     */
    public CelestialBody removeSolarSystemNameFromCelestialBody(String bodyId, SolarSystem solarSystem) {
        CelestialBody body = getCelestialBody(bodyId);

        Map<String, String> systemNames = body.getSolarSystemNames();
        systemNames.remove(solarSystem.getSystemId());
        body.setSolarSystemNames(systemNames);

        return saveCelestialBody(body);
    }

    /**
     * Removes the provided {@link SolarSystem} from all Celestial Bodies that were its members.
     *
     * @param solarSystem the Solar System that was deleted and needs to be scrubbed from the Bodies
     */
    public void deleteSolarSystemFromAllCelestialBodies(SolarSystem solarSystem) {
        List<CelestialBody> bodyList = solarSystem.getCelestialBodies();
        for (CelestialBody body : bodyList) {
            body.getSolarSystemNames().remove(solarSystem.getSystemId());
            saveCelestialBody(body);
        }
    }

    /**
     * When a {@link SolarSystem} name has been changed, updates the name for all its {@link CelestialBody} members.
     *
     * @param solarSystem the Solar System that was updated with a new name
     * @return the {@link List} of updated Celestial Bodies that should now be saved back into the {@link SolarSystem}
     */
    public List<CelestialBody> updateSolarSystemNameInCelestialBodies(SolarSystem solarSystem) {
        List<CelestialBody> updatedBodies = new ArrayList<>();
        List<CelestialBody> bodyList = solarSystem.getCelestialBodies();

        for (CelestialBody body : bodyList) {
            Map<String, String> systemNames = body.getSolarSystemNames();
            systemNames.put(solarSystem.getSystemId(), solarSystem.getSystemName());
            body.setSolarSystemNames(systemNames);
            saveCelestialBody(body);
            updatedBodies.add(body);
        }

        return updatedBodies;
    }

    /**
     * @deprecated
     * Updates all {@link CelestialBody} items with a newly updated {@link SolarSystem} object.
     *
     * @param solarSystem the Solar System that was updated and needs to be changed in the lists.
     */
    @Deprecated
    public void updateSolarSystemInCelestialBodies(SolarSystem solarSystem) {
        List<CelestialBody> bodyList = solarSystem.getCelestialBodies();
        String systemId = solarSystem.getSystemId();
        for (CelestialBody body : bodyList) {
            int index = -1;
            List<SolarSystem> systemsList = new ArrayList<SolarSystem>();
            for (int i = 0; i < systemsList.size(); i++) {
                if (systemsList.get(i).getSystemId().equals(solarSystem.getSystemId())) {
                    index = i;
                }
            }
            if (index != -1) {
                systemsList.remove(index);
                systemsList.add(index, solarSystem);
//                body.setSolarSystemNames(systemsList);
                saveCelestialBody(body);
            }
        }
    }

    /**
     * Deletes every {@link CelestialBody} with a provided id.
     *
     * @param bodyIds the List of ids to be deleted
     */
    public void deleteCelestialBodiesList(List<String> bodyIds) {
        List<CelestialBody> celestialBodyList = new ArrayList<>();
        //iterate through the bodyIds
        for (String bodyId: bodyIds){
            // set a body Id to a new celestialbody object
            CelestialBody bodyToGet = new CelestialBody();
            bodyToGet.setId(bodyId);
            // Add to the list
            celestialBodyList.add(bodyToGet);
        }
        dynamoDbMapper.batchDelete(celestialBodyList);
    }
}
