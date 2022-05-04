package com.aetherark.service.lambda;


import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.CreateCelestialBodyRequest;
import com.aetherark.service.models.results.CreateCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateCelestialBodyActivityProvider
        implements RequestHandler<CreateCelestialBodyRequest, CreateCelestialBodyResult> {

    private static ServiceComponent serviceComponent;

    public CreateCelestialBodyActivityProvider() {

    }

    @Override
    public CreateCelestialBodyResult handleRequest(
            final CreateCelestialBodyRequest createCelestialBodyRequest, Context context) {
        return getServiceComponent().provideCreateCelestialBodyActivity()
                .handleRequest(createCelestialBodyRequest, context);
    }


    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
