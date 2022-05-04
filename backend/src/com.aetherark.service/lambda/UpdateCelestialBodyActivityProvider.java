package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.UpdateCelestialBodyRequest;
import com.aetherark.service.models.results.UpdateCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateCelestialBodyActivityProvider
        implements RequestHandler<UpdateCelestialBodyRequest, UpdateCelestialBodyResult> {

    private static ServiceComponent serviceComponent;

    public UpdateCelestialBodyActivityProvider() {

    }

    @Override
    public UpdateCelestialBodyResult handleRequest(
            final UpdateCelestialBodyRequest updateCelestialBodyRequest, Context context) {
        return getServiceComponent().provideUpdateCelestialBodyActivity()
                .handleRequest(updateCelestialBodyRequest, context);
    }



    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
