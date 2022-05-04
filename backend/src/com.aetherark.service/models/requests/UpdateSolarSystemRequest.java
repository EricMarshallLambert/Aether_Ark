package com.aetherark.service.models.requests;

import java.util.Objects;

public class UpdateSolarSystemRequest {
    private String username;
    private String solarSystemId;
    private String newSolarSystemName;
    private String celestialBodyDistanceToUpdate;
    private Integer newDistanceFromCenter;
    private String celestialBodyIdToAddToSolarSystem;
    private String celestialBodyIdToRemoveFromSolarSystem;




    public UpdateSolarSystemRequest(Builder builder) {
        this.username = builder.username;
        this.solarSystemId = builder.solarSystemId;
        this.newSolarSystemName = builder.newSolarSystemName;
        this.celestialBodyDistanceToUpdate = builder.celestialBodyDistanceToUpdate;
        this.newDistanceFromCenter = builder.newDistanceFromCenter;
        this.celestialBodyIdToAddToSolarSystem = builder.celestialBodyIdToAddToSolarSystem;
        this.celestialBodyIdToRemoveFromSolarSystem = builder.celestialBodyIdToRemoveFromSolarSystem;
    }

    public UpdateSolarSystemRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSolarSystemId() {
        return solarSystemId;
    }

    public void setSolarSystemId(String solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    public String getNewSolarSystemName() {
        return newSolarSystemName;
    }

    public void setNewSolarSystemName(String newSolarSystemName) {
        this.newSolarSystemName = newSolarSystemName;
    }

    public String getCelestialBodyDistanceToUpdate() {
        return celestialBodyDistanceToUpdate;
    }

    public void setCelestialBodyDistanceToUpdate(String celestialBodyDistanceToUpdate) {
        this.celestialBodyDistanceToUpdate = celestialBodyDistanceToUpdate;
    }

    public Integer getNewDistanceFromCenter() {
        return newDistanceFromCenter;
    }

    public void setNewDistanceFromCenter(Integer newDistanceFromCenter) {
        this.newDistanceFromCenter = newDistanceFromCenter;
    }

    public String getCelestialBodyIdToAddToSolarSystem() {
        return celestialBodyIdToAddToSolarSystem;
    }

    public void setCelestialBodyIdToAddToSolarSystem(String celestialBodyIdToAddToSolarSystem) {
        this.celestialBodyIdToAddToSolarSystem = celestialBodyIdToAddToSolarSystem;
    }

    public String getCelestialBodyIdToRemoveFromSolarSystem() {
        return celestialBodyIdToRemoveFromSolarSystem;
    }

    public void setCelestialBodyIdToRemoveFromSolarSystem(String celestialBodyIdToRemoveFromSolarSystem) {
        this.celestialBodyIdToRemoveFromSolarSystem = celestialBodyIdToRemoveFromSolarSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateSolarSystemRequest that = (UpdateSolarSystemRequest) o;
        return getUsername().equals(that.getUsername()) && getSolarSystemId().equals(that.getSolarSystemId()) && getNewSolarSystemName().equals(that.getNewSolarSystemName()) && getCelestialBodyDistanceToUpdate().equals(that.getCelestialBodyDistanceToUpdate()) && getNewDistanceFromCenter().equals(that.getNewDistanceFromCenter()) && getCelestialBodyIdToAddToSolarSystem().equals(that.getCelestialBodyIdToAddToSolarSystem()) && getCelestialBodyIdToRemoveFromSolarSystem().equals(that.getCelestialBodyIdToRemoveFromSolarSystem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSolarSystemId(), getNewSolarSystemName(), getCelestialBodyDistanceToUpdate(), getNewDistanceFromCenter(), getCelestialBodyIdToAddToSolarSystem(), getCelestialBodyIdToRemoveFromSolarSystem());
    }

    @Override
    public String toString() {
        return "UpdateSolarSystemRequest{" +
                "username='" + username + '\'' +
                ", solarSystemId='" + solarSystemId + '\'' +
                ", newSolarSystemName='" + newSolarSystemName + '\'' +
                ", celestialBodyDistanceToUpdate='" + celestialBodyDistanceToUpdate + '\'' +
                ", newDistanceFromStar=" + newDistanceFromCenter +
                ", celestialBodyIdToAddToSolarSystem='" + celestialBodyIdToAddToSolarSystem + '\'' +
                ", celestialBodyIdToRemoveFromSolarSystem='" + celestialBodyIdToRemoveFromSolarSystem + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String solarSystemId;
        private String newSolarSystemName;
        private String celestialBodyDistanceToUpdate;
        private Integer newDistanceFromCenter;
        private String celestialBodyIdToAddToSolarSystem;
        private String celestialBodyIdToRemoveFromSolarSystem;

        private Builder() {

        }

        public Builder withUserName(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withSolarSystemId(String systemIdToUse) {
            this.solarSystemId = systemIdToUse;
            return this;
        }

        public Builder withNewSolarSystemName(String newNameToUse) {
            this.newSolarSystemName = newNameToUse;
            return this;
        }

        public Builder withCelestialBodyDistanceToUpdate(String celestialBodyIdDistanceToUpdateToUse) {
            this.celestialBodyDistanceToUpdate = celestialBodyIdDistanceToUpdateToUse;
            return this;
        }

        public Builder withNewDistanceFromCenter(Integer newDistanceToUse) {
            this.newDistanceFromCenter = newDistanceToUse;
            return this;
        }

        public Builder withCelestialBodyIdToAddToSolarSystem(String celestialBodyIdToAddToSolarSystemToUse) {
            this.celestialBodyIdToAddToSolarSystem = celestialBodyIdToAddToSolarSystemToUse;
            return this;
        }

        public Builder withCelestialBodyIdToRemoveFromSolarSystem(String bodyToRemove) {
            this.celestialBodyIdToRemoveFromSolarSystem = bodyToRemove;
            return this;
        }

        public UpdateSolarSystemRequest build() {
            return new UpdateSolarSystemRequest(this);
        }

    }

}