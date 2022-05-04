package com.aetherark.service.models;

import java.util.List;
import java.util.Objects;

/**
 * UserModel for result objects.
 */
public class UserModel {
    private String name;
    private String email;
    private List<String> solarSystemIds;
    private List<String> celestialBodyIds;

    /**
     * No argument constructor for dependency injection.
     */
    public UserModel(){
    }

    private UserModel(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.solarSystemIds = builder.solarSystemIds;
        this.celestialBodyIds = builder.celestialBodyIds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String email;
        private List<String> solarSystemIds;
        private List<String> celestialBodyIds;
        
        public Builder withName(String nameToUse){
            this.name = nameToUse;
            return this;
        }
        
        public Builder withEmail(String emailToUse){
            this.email = emailToUse;
            return this;
        }
        
        public Builder withSolarSystemIds(List<String> solarSystemIdsToUse){
            this.solarSystemIds = solarSystemIdsToUse;
            return this;
        }

        public Builder withCelestialBodyIds(List<String> celestialBodyIdsToUse) {
            this.celestialBodyIds = celestialBodyIdsToUse;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSolarSystemIds() {
        return solarSystemIds;
    }

    public void setSolarSystemIds(List<String> solarSystemIds) {
        this.solarSystemIds = solarSystemIds;
    }

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
        UserModel userModel = (UserModel) o;
        return Objects.equals(getName(), userModel.getName()) && Objects.equals(getEmail(), userModel.getEmail()) && Objects.equals(getSolarSystemIds(), userModel.getSolarSystemIds()) && Objects.equals(getCelestialBodyIds(), userModel.getCelestialBodyIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getSolarSystemIds(), getCelestialBodyIds());
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", solarSystemIds=" + solarSystemIds +
                ", celestialBodyIds=" + celestialBodyIds +
                '}';
    }
}
