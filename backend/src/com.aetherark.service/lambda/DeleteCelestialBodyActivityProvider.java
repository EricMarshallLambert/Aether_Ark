package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.DeleteCelestialBodyRequest;
import com.aetherark.service.models.results.DeleteCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteCelestialBodyActivityProvider
        implements RequestHandler<DeleteCelestialBodyRequest, DeleteCelestialBodyResult> {

    private static ServiceComponent serviceComponent;

    public DeleteCelestialBodyActivityProvider() {

    }

    @Override
    public DeleteCelestialBodyResult handleRequest(
            final DeleteCelestialBodyRequest deleteCelestialBodyRequest, Context context) {
        return getServiceComponent().provideDeleteCelestialBodyActivity()
                .handleRequest(deleteCelestialBodyRequest, context);
    }


    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
