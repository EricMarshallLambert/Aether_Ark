package com.aetherark.service.models.results;

import com.aetherark.service.models.UserModel;

import java.util.Objects;

public class CreateUserResult {
    private UserModel user;

    private CreateUserResult(Builder builder) {
        this.user = builder.user;
    }

    public UserModel getUserModel() {
        return user;
    }

    public void setUserModel(UserModel userModel) {
        this.user = userModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserResult that = (CreateUserResult) o;
        return Objects.equals(getUserModel(), that.getUserModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserModel());
    }

    @Override
    public String toString() {
        return "CreateUserResult{" +
                "userModel=" + user +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserModel user;

        private Builder(){

        }

        public Builder withUser(UserModel userToUse) {
            this.user = userToUse;
            return this;
        }

        public CreateUserResult build() {
            return new CreateUserResult(this);
        }
    }
}
