package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.UpdateSolarSystemRequest;
import com.aetherark.service.models.results.UpdateSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateSolarSystemActivityProvider implements RequestHandler<UpdateSolarSystemRequest, UpdateSolarSystemResult> {

    private static ServiceComponent serviceComponent;

    public UpdateSolarSystemActivityProvider() {

    }

    @Override
    public UpdateSolarSystemResult handleRequest(final UpdateSolarSystemRequest updateSolarSystemRequest, Context context) {
        return getServiceComponent().provideUpdateSolarSystemActivity().handleRequest(updateSolarSystemRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
