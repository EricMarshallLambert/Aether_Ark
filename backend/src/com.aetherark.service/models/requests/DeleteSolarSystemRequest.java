package com.aetherark.service.models.requests;

import java.util.Objects;

public class DeleteSolarSystemRequest {

    private String username;
    private String systemId;

    public DeleteSolarSystemRequest() {
    }

    public DeleteSolarSystemRequest(Builder builder) {
        this.username = builder.username;
        this.systemId = builder.systemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteSolarSystemRequest that = (DeleteSolarSystemRequest) o;
        return getUsername().equals(that.getUsername()) && getSystemId().equals(that.getSystemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSystemId());
    }

    @Override
    public String toString() {
        return "DeleteSolarSystemRequest{" +
                "username='" + username + '\'' +
                ", systemId='" + systemId + '\'' +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String systemId;


        private Builder() {

        }

        public Builder withUserName(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withSystemId(String systemIdToUse) {
            this.systemId = systemIdToUse;
            return this;
        }

        public DeleteSolarSystemRequest build() {
            return new DeleteSolarSystemRequest(this);
        }


    }

}
