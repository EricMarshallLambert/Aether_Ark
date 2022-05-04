package com.aetherark.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents an object in the Users table
 */
@DynamoDBTable(tableName = "Users")
public class User {
    private String name;
    private String email;
    private List<String> solarSystemIds;
    private List<String> celestialBodyIds;
    private static final String NAME = "username";
    private static final String EMAIL = "email";
    private static final String SOLAR_SYSTEM_IDS = "solarSystemIds";
    private static final String CELESTIAL_BODY_IDS = "celestialBodyIds";

    @DynamoDBHashKey(attributeName = NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = SOLAR_SYSTEM_IDS)
    public List<String> getSolarSystemIds() {
        return solarSystemIds;
    }

    public void setSolarSystemIds(List<String> solarSystemIds) {
        this.solarSystemIds = solarSystemIds;
    }

    @DynamoDBAttribute(attributeName = CELESTIAL_BODY_IDS)
    public List<String> getCelestialBodyIds() {
        return celestialBodyIds;
    }

    public void setCelestialBodyIds(List<String> celestialBodyIds) {
        this.celestialBodyIds = celestialBodyIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getSolarSystemIds(), user.getSolarSystemIds()) && Objects.equals(getCelestialBodyIds(), user.getCelestialBodyIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getSolarSystemIds(), getCelestialBodyIds());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", solarSystemIds=" + solarSystemIds +
                ", celestialBodyIds=" + celestialBodyIds +
                '}';
    }
}
