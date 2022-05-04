package com.aetherark.service.models.requests;

import java.util.Objects;

public class GetUserRequest {
    private String username;
    private String email;

    public GetUserRequest() {

    }

    private GetUserRequest(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetUserRequest request = (GetUserRequest) o;
        return Objects.equals(getUsername(), request.getUsername()) && Objects.equals(email, request.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), email);
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        String username;
        String email;

        private Builder(){

        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withEmail(String emailToUse) {
            this.email = emailToUse;
            return this;
        }


        public GetUserRequest build() {
            return new GetUserRequest(this);
        }
    }

}
