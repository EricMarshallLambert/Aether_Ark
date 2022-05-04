package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.CreateSolarSystemRequest;
import com.aetherark.service.models.results.CreateSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateSolarSystemActivityProvider implements RequestHandler<CreateSolarSystemRequest, CreateSolarSystemResult> {

    private static ServiceComponent serviceComponent;

    public CreateSolarSystemActivityProvider() {

    }

    @Override
    public CreateSolarSystemResult handleRequest(final CreateSolarSystemRequest createSolarSystemRequest, Context context) {
        return getServiceComponent().provideCreateSolarSystemActivity().handleRequest(createSolarSystemRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        if(serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
