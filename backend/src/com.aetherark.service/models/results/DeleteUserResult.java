package com.aetherark.service.models.results;

import com.aetherark.service.models.UserModel;

public class DeleteUserResult {
    private UserModel user;

    DeleteUserResult() {

    }

    private DeleteUserResult(Builder builder) {
        this.user = builder.user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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

        public DeleteUserResult build() {
            return new DeleteUserResult(this);
        }
    }

}
