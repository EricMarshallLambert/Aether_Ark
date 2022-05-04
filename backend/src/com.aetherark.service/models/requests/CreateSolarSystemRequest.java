package com.aetherark.service.models.requests;


import java.util.Objects;

public class CreateSolarSystemRequest {
    private String username;
    private String systemName;



    public CreateSolarSystemRequest() {
    }

    public CreateSolarSystemRequest(Builder builder) {
        this.username = builder.username;
        this.systemName = builder.systemName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateSolarSystemRequest that = (CreateSolarSystemRequest) o;
        return getUsername().equals(that.getUsername()) && getSystemName().equals(that.getSystemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getSystemName());
    }

    @Override
    public String toString() {
        return "CreateSolarSystemRequest{" +
                "username='" + username + '\'' +
                ", systemName='" + systemName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String systemName;


        private Builder() {

        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withSystemName(String systemNameToUse) {
            this.systemName = systemNameToUse;
            return this;
        }

        public CreateSolarSystemRequest build() {
            return new CreateSolarSystemRequest(this);
        }
    }
}
