package com.aetherark.service.models.requests;

import java.util.Objects;

public class DeleteUserRequest {
    private String username;
    private String email;

    public DeleteUserRequest() {

    }

    private DeleteUserRequest(Builder builder) {
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
        DeleteUserRequest that = (DeleteUserRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }

    @Override
    public String toString() {
        return "DeleteUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String email;

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


        public DeleteUserRequest build() {
            return new DeleteUserRequest(this);
        }
    }
}
