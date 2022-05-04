package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.GetCelestialBodyRequest;
import com.aetherark.service.models.results.GetCelestialBodyResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetCelestialBodyActivityProvider
        implements RequestHandler<GetCelestialBodyRequest, GetCelestialBodyResult> {

    private static ServiceComponent serviceComponent;

    public GetCelestialBodyActivityProvider() {

    }

    @Override
    public GetCelestialBodyResult handleRequest(
            final GetCelestialBodyRequest getCelestialBodyRequest, Context context) {
        return getServiceComponent().provideGetCelestialBodyActivity().handleRequest(getCelestialBodyRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}
