package com.aetherark.service.models.requests;

import com.aetherark.service.models.Composition;

import java.util.Objects;

public class UpdateCelestialBodyRequest {

    private String username;
    private String celestialBodyId;
    private String name;
    private Integer diameter;
    private Integer mass;
    private Composition composition;

    public UpdateCelestialBodyRequest() {
    }

    public UpdateCelestialBodyRequest(Builder builder) {
        this.username = builder.username;
        this.celestialBodyId = builder.id;
        this.name = builder.name;
        this.diameter = builder.diameter;
        this.mass = builder.mass;
        this.composition = builder.composition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCelestialBodyId() {
        return celestialBodyId;
    }

    public void setCelestialBodyId(String celestialBodyId) {
        this.celestialBodyId = celestialBodyId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCelestialBodyRequest that = (UpdateCelestialBodyRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(celestialBodyId, that.celestialBodyId)
                && Objects.equals(name, that.name) && Objects.equals(diameter, that.diameter)
                && Objects.equals(mass, that.mass) && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, celestialBodyId, name, diameter, mass, composition);
    }

    @Override
    public String toString() {
        return "UpdateCelestialBodyRequest{" +
                "username='" + username + '\'' +
                ", celestialBodyId='" + celestialBodyId + '\'' +
                ", name='" + name + '\'' +
                ", size=" + diameter +
                ", mass=" + mass +
                ", composition='" + composition + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String id;
        private String username;
        private String name;
        private Integer diameter;
        private Integer mass;
        private Composition composition;

        private Builder() {

        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withDiameter(Integer diameterToUse) {
            this.diameter = diameterToUse;
            return this;
        }

        public Builder withMass(Integer massToUse) {
            this.mass = massToUse;
            return this;
        }

        public Builder withComposition(Composition compositionToUse) {
            this.composition = compositionToUse;
            return this;
        }

        public UpdateCelestialBodyRequest build() {
            return new UpdateCelestialBodyRequest(this);
        }
    }
}