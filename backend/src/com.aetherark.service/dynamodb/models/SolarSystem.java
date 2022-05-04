package com.aetherark.service.dynamodb.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@DynamoDBTable(tableName = "SolarSystems")
public class SolarSystem {

    public static final String USERNAME_INDEX = "GetAllSolarSystemsForUser";
    private String systemId;
    private String systemName;
    private List<CelestialBody> celestialBodies;
    private Map<String, Integer> distanceFromCenter;
    private String username;

    public SolarSystem(String systemId, String systemName, List<CelestialBody> celestialBodies, Map<String, Integer> distanceFromCenter, String username) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.celestialBodies = celestialBodies;
        this.distanceFromCenter = distanceFromCenter;
        this.username = username;
    }

    public SolarSystem() {
    }

    @DynamoDBHashKey(attributeName = "solarSystemId")
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @DynamoDBAttribute(attributeName = "systemName")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @DynamoDBAttribute(attributeName = "celestialBodies")
    public List<CelestialBody> getCelestialBodies() {
        return celestialBodies;
    }

    public void setCelestialBodies(List<CelestialBody> celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    @DynamoDBAttribute(attributeName = "distanceFromCenter")
    public Map<String, Integer> getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(Map<String, Integer> distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = USERNAME_INDEX, attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolarSystem that = (SolarSystem) o;
        return getSystemId().equals(that.getSystemId()) && getSystemName().equals(that.getSystemName()) && getCelestialBodies().equals(that.getCelestialBodies()) && getDistanceFromCenter().equals(that.getDistanceFromCenter()) && getUsername().equals(that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSystemId(), getSystemName(), getCelestialBodies(), getDistanceFromCenter(), getUsername());
    }

    @Override
    public String toString() {
        return "SolarSystem{" +
                "systemId='" + systemId + '\'' +
                ", systemName='" + systemName + '\'' +
                ", celestialBodies=" + celestialBodies +
                ", distanceFromCenter=" + distanceFromCenter +
                ", username='" + username + '\'' +
                '}';
    }
}
