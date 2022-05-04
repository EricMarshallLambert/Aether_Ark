package com.aetherark.service.models;

import com.aetherark.service.dynamodb.models.SolarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CelestialBodyModel {
    private String bodyId;
    private String name;
    private Integer diameter;
    private Integer mass;
    private Composition composition;
    private Map<String, String> solarSystemNames;

//    private List<SolarSystem> memberSolarSystems;

    public CelestialBodyModel() {

    }

    public CelestialBodyModel(Builder builder) {
        this.bodyId = builder.id;
        this.name = builder.name;
        this.diameter = builder.diameter;
        this.mass = builder.mass;
        this.composition = builder.composition;
        this.solarSystemNames = builder.solarSystemNames;
    }

    public String getBodyId() {
        return bodyId;
    }

    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

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
        CelestialBodyModel that = (CelestialBodyModel) o;
        return bodyId.equals(that.bodyId) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyId, name);
    }

    @Override
    public String toString() {
        return "CelestialBodyModel{" +
                "bodyId='" + bodyId + '\'' +
                ", name='" + name + '\'' +
                ", diameter=" + diameter +
                ", mass=" + mass +
                ", composition='" + composition + '\'' +
                ", memberSolarSystems=" + solarSystemNames +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private Integer diameter;
        private Integer mass;
        private Composition composition;
        private Map<String, String> solarSystemNames;

        public Builder withId(String givenId) {
            this.id = givenId;
            return this;
        }

        public Builder withName(String givenName) {
            this.name = givenName;
            return this;
        }

        public Builder withDiameter(Integer givenDiameter) {
            this.diameter = givenDiameter;
            return this;
        }

        public Builder withMass(Integer givenMass) {
            this.mass = givenMass;
            return this;
        }

        public Builder withComposition(Composition givenComposition) {
            this.composition = givenComposition;
            return this;
        }

        public Builder withSolarSystems(Map<String, String> givenSolarSystems) {
            this.solarSystemNames = givenSolarSystems;
            return this;
        }

        public CelestialBodyModel build() {
            return new CelestialBodyModel(this);
        }
    }
}
