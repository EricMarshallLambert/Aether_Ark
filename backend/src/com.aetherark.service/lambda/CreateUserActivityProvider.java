package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.CreateUserRequest;
import com.aetherark.service.models.results.CreateUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {
    public static ServiceComponent dependencies;

    public CreateUserActivityProvider() {

    }

    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        return getDependencies().provideCreateUserActivity().handleRequest(createUserRequest, context);
    }

    private ServiceComponent getDependencies() {
        if (dependencies == null) {
            dependencies = DaggerServiceComponent.create();
        }
        return dependencies;
    }
}

