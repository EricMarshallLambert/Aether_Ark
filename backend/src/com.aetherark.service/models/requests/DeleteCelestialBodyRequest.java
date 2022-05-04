package com.aetherark.service.models.requests;

import java.util.Objects;

public class DeleteCelestialBodyRequest {

    private String username;
    private String celestialBodyId;

    public DeleteCelestialBodyRequest() {

    }

    public DeleteCelestialBodyRequest(Builder builder) {
        this.username = builder.username;
        this.celestialBodyId = builder.username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteCelestialBodyRequest that = (DeleteCelestialBodyRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(celestialBodyId, that.celestialBodyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, celestialBodyId);
    }

    @Override
    public String toString() {
        return "DeleteCelestialBodyRequest{" +
                "username='" + username + '\'' +
                ", celestialBodyId='" + celestialBodyId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String id;

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

        public DeleteCelestialBodyRequest build() {
            return new DeleteCelestialBodyRequest(this);
        }
    }
}
