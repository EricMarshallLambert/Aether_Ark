package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.GetSolarSystemRequest;
import com.aetherark.service.models.results.GetSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetSolarSystemActivityProvider implements RequestHandler<GetSolarSystemRequest, GetSolarSystemResult> {

    private static ServiceComponent serviceComponent;

    public GetSolarSystemActivityProvider() {

    }

    @Override
    public GetSolarSystemResult handleRequest(final GetSolarSystemRequest getSolarSystemRequest, Context context) {
        return getServiceComponent().provideGetSolarSystemActivity().handleRequest(getSolarSystemRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }
}



