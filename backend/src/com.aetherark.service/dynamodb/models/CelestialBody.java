package com.aetherark.service.dynamodb.models;

import com.aetherark.service.models.Composition;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@DynamoDBTable(tableName = "CelestialBodies")
public class CelestialBody {

    private String id;
    private String name;
    private String username;
    private Integer diameter;
    private Integer mass;
    private Composition composition;
    private Map<String, String> solarSystemNames;

    @DynamoDBHashKey(attributeName = "celestialBodyId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "diameter")
    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    @DynamoDBAttribute(attributeName = "mass")
    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "composition")
    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    @DynamoDBAttribute(attributeName = "solar_systems")
    public Map<String, String> getSolarSystemNames() {
        return solarSystemNames;
    }

    public void setSolarSystemNames(Map<String, String> solarSystemNames) {
        this.solarSystemNames = solarSystemNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody that = (CelestialBody) o;
        return id.equals(that.id) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "CelestialBody{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", diameter=" + diameter +
                ", mass=" + mass +
                ", composition=" + composition +
                ", memberSolarSystems=" + solarSystemNames +
                '}';
    }
}
