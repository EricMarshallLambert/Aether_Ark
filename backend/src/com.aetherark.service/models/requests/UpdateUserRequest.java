package com.aetherark.service.models.requests;

import java.util.Objects;

public class UpdateUserRequest {
    private String username;
    private String email;
    private String updatedEmail;

    public UpdateUserRequest() {

    }

    private UpdateUserRequest(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.updatedEmail = builder.updatedEmail;
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

    public String getUpdatedEmail() {
        return updatedEmail;
    }

    public void setUpdatedEmail(String updatedEmail) {
        this.updatedEmail = updatedEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateUserRequest that = (UpdateUserRequest) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
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
        private String updatedEmail;

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

        public Builder withUpdatedEmail(String updatedEmailToUse) {
            this.updatedEmail = updatedEmailToUse;
            return this;
        }


        public UpdateUserRequest build() {
            return new UpdateUserRequest(this);
        }
    }
}
