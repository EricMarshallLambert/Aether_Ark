package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.GetUserRequest;
import com.aetherark.service.models.results.GetUserResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetUserActivityProvider  implements RequestHandler<GetUserRequest, GetUserResult> {
    public static ServiceComponent dependencies;

    public GetUserActivityProvider() {

    }

    @Override
    public GetUserResult handleRequest(final GetUserRequest getUserRequest, Context context) {
        return getDependencies().provideGetUserActivity().handleRequest(getUserRequest, context);
    }

    private ServiceComponent getDependencies() {
        if (dependencies == null) {
            dependencies = DaggerServiceComponent.create();
        }
        return dependencies;
    }
}
