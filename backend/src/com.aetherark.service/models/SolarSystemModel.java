package com.aetherark.service.models;

import com.aetherark.service.dynamodb.models.CelestialBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SolarSystemModel {
    private String systemId;
    private String systemName;
    private List<CelestialBody> celestialBodies;
    private Map<String, Integer> distanceFromCenter;
    private String username;

    public SolarSystemModel() {

    }

    public SolarSystemModel(Builder builder) {
        this.systemId = builder.systemId;
        this.systemName = builder.systemName;;
        this.celestialBodies = builder.celestialBodies;
        this.distanceFromCenter = builder.distanceFromCenter;
        this.username = builder.username;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<CelestialBody> getCelestialBodies() {
        return celestialBodies;
    }

    public void setCelestialBodies(List<CelestialBody> celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    public Map<String, Integer> getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(Map<String, Integer> distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

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
        SolarSystemModel that = (SolarSystemModel) o;
        return getSystemId().equals(that.getSystemId()) && getSystemName().equals(that.getSystemName()) && getCelestialBodies().equals(that.getCelestialBodies()) && getDistanceFromCenter().equals(that.getDistanceFromCenter()) && getUsername().equals(that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSystemId(), getSystemName(), getCelestialBodies(), getDistanceFromCenter(), getUsername());
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String systemId;
        private String systemName;
        private List<CelestialBody> celestialBodies;
        private Map<String, Integer> distanceFromCenter;
        private String username;

        public Builder withSystemId(String idToUse) {
            this.systemId = idToUse;
            return this;
        }

        public Builder withSystemName(String nameToUse) {
            this.systemName = nameToUse;
            return this;
        }

        public Builder withCelestialBodies(List<CelestialBody> bodiesToUse) {
            this.celestialBodies = bodiesToUse;
            return this;
        }

        public Builder withDistanceFromCenter(Map<String, Integer> distancesToUse) {
            this.distanceFromCenter = distancesToUse;
            return this;
        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public SolarSystemModel build() {
            return new SolarSystemModel(this);
        }

    }
}

