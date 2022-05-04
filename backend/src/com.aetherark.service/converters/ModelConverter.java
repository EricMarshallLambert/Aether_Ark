package com.aetherark.service.converters;

import com.aetherark.service.dynamodb.models.CelestialBody;
import com.aetherark.service.dynamodb.models.SolarSystem;
import com.aetherark.service.dynamodb.models.User;
import com.aetherark.service.models.CelestialBodyModel;
import com.aetherark.service.models.SolarSystemModel;
import com.aetherark.service.models.UserModel;

import java.util.ArrayList;
import java.util.List;


public class ModelConverter {

    /**
     * Converts a provided {@link CelestialBody} into a {@link CelestialBodyModel} representation.
     * @param body the Celestial Body to convert
     * @return the converted model
     */
    public CelestialBodyModel toCelestialBodyModel(CelestialBody body) {
        return CelestialBodyModel.builder()
                .withId(body.getId())
                .withName(body.getName())
                .withDiameter(body.getDiameter())
                .withMass(body.getMass())
                .withComposition(body.getComposition())
                .withSolarSystems(body.getSolarSystemNames())
                .build();
    }

    /**
     * Converts a provided {@link SolarSystem} into a {@link SolarSystemModel} representation.
     * @param system the Solar System to convert
     * @return the converted model
     */
    public SolarSystemModel toSolarSystemModel(SolarSystem system) {
        return SolarSystemModel.builder()
                .withSystemId(system.getSystemId())
                .withSystemName(system.getSystemName())
                .withCelestialBodies(system.getCelestialBodies())
                .withDistanceFromCenter(system.getDistanceFromCenter())
                .withUsername(system.getUsername())
                .build();
    }

    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     * @param user the User object to convert
     * @return the converted model
     */
    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withSolarSystemIds(user.getSolarSystemIds())
                .withCelestialBodyIds(user.getCelestialBodyIds())
                .build();
    }

    /**
     * Convenience method for converting a list of SolarSystems into a list of their Models
     * @param systemList the list of Solar Systems to convert
     * @return a fully converted list of Solar System Models
     */
    public List<SolarSystemModel> toSolarSystemModelList(List<SolarSystem> systemList) {
        List<SolarSystemModel> modelList = new ArrayList<>();
        for (SolarSystem system : systemList) {
            modelList.add(this.toSolarSystemModel(system));
        }

        return modelList;
    }

    /**
     * Convenience method for converting a list of CelestialBodies into a list of their Models
     * @param bodyList the list of Celestial Bodies to convert
     * @return a fully converted list of Celestial Body Models
     */
    public List<CelestialBodyModel> toCelestialBodyModelList(List<CelestialBody> bodyList) {
        List<CelestialBodyModel> modelList = new ArrayList<>();
        for (CelestialBody body : bodyList) {
            modelList.add(this.toCelestialBodyModel(body));
        }

        return modelList;
    }
}
