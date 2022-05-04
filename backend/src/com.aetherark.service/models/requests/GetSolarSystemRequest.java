package com.aetherark.service.models.requests;

import java.util.Objects;

public class GetSolarSystemRequest {
    private String username;
    private String systemId;
    private boolean getAll;



    public GetSolarSystemRequest() {
    }

    public GetSolarSystemRequest(Builder builder) {
        this.username = builder.username;
        this.systemId = builder.systemId;
        this.getAll = builder.getAll;

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

    public boolean isGetAll() {
        return getAll;
    }

    public void setGetAll(boolean getAll) {
        this.getAll = getAll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSolarSystemRequest that = (GetSolarSystemRequest) o;
        return isGetAll() == that.isGetAll() && getUsername().equals(that.getUsername()) && getSystemId().equals(that.getSystemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSystemId(), isGetAll());
    }

    @Override
    public String toString() {
        return "GetSolarSystemRequest{" +
                "username='" + username + '\'' +
                ", systemId='" + systemId + '\'' +
                ", getAll=" + getAll +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String systemId;
        private boolean getAll;


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

        public Builder withGetAll(boolean getAll) {
            this.getAll = getAll;
            return this;
        }

        public GetSolarSystemRequest build() {
            return new GetSolarSystemRequest(this);
        }

    }
}
