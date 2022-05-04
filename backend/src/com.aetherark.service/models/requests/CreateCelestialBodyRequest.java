package com.aetherark.service.models.requests;

import com.aetherark.service.models.Composition;

import java.util.Objects;

public class CreateCelestialBodyRequest {
    private String username;
    private String name;
    private Integer diameter;
    private Integer mass;
    private Composition composition;

    public CreateCelestialBodyRequest() {

    }

    public CreateCelestialBodyRequest(Builder builder) {
        this.username = builder.username;
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
        CreateCelestialBodyRequest that = (CreateCelestialBodyRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(name, that.name)
                && Objects.equals(diameter, that.diameter) && Objects.equals(mass, that.mass)
                && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, diameter, mass, composition);
    }

    @Override
    public String toString() {
        return "CreateCelestialBodyRequest{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", diameter=" + diameter +
                ", mass=" + mass +
                ", composition='" + composition + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String name;
        private Integer diameter;
        private Integer mass;
        private Composition composition;

        private Builder() {

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

        public CreateCelestialBodyRequest build() {
            return new CreateCelestialBodyRequest(this);
        }
    }
}
