package com.aetherark.service.models.results;

import com.aetherark.service.models.UserModel;

public class UpdateUserResult {
    private UserModel user;

    private UpdateUserResult(Builder builder) {
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

        public UpdateUserResult build() {
            return new UpdateUserResult(this);
        }
    }
}
