package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.UpdateUserRequest;
import com.aetherark.service.models.results.UpdateUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateUserActivityProvider implements RequestHandler<UpdateUserRequest, UpdateUserResult> {
    public static ServiceComponent dependencies;

    public UpdateUserActivityProvider() {

    }

    @Override
    public UpdateUserResult handleRequest(final UpdateUserRequest UpdateUserRequest, Context context) {
        return getDependencies().provideUpdateUserActivity().handleRequest(UpdateUserRequest, context);
    }

    private ServiceComponent getDependencies() {
        if (dependencies == null) {
            dependencies = DaggerServiceComponent.create();
        }
        return dependencies;
    }
}

