package com.aetherark.service.models.results;

import com.aetherark.service.models.UserModel;

public class GetUserResult {
    private UserModel user;

    private GetUserResult(Builder builder) {
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

        public Builder withUser(UserModel userToUse) {
            this.user = userToUse;
            return this;
        }

        public GetUserResult build() {
            return new GetUserResult(this);
        }
    }
}
