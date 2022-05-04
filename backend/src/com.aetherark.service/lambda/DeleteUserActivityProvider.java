package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.DeleteUserRequest;
import com.aetherark.service.models.results.DeleteUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteUserActivityProvider implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
    public static ServiceComponent dependencies;

    public DeleteUserActivityProvider() {

    }

    @Override
    public DeleteUserResult handleRequest(final DeleteUserRequest deleteUserRequest, Context context) {
        return getDependencies().provideDeleteUserActivity().handleRequest(deleteUserRequest, context);
    }

    private ServiceComponent getDependencies() {
        if (dependencies == null) {
            dependencies = DaggerServiceComponent.create();
        }
        return dependencies;
    }
}

