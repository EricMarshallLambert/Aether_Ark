package com.aetherark.service.lambda;

import com.aetherark.service.dependency.DaggerServiceComponent;
import com.aetherark.service.dependency.ServiceComponent;
import com.aetherark.service.models.requests.DeleteSolarSystemRequest;
import com.aetherark.service.models.results.DeleteSolarSystemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteSolarSystemActivityProvider implements RequestHandler<DeleteSolarSystemRequest, DeleteSolarSystemResult> {

    private static ServiceComponent serviceComponent;

    public DeleteSolarSystemActivityProvider() {

    }


    @Override
    public DeleteSolarSystemResult handleRequest(final DeleteSolarSystemRequest deleteSolarSystemRequest, Context context) {
        return getServiceComponent().provideDeleteSolarSystemActivity().handleRequest(deleteSolarSystemRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }

        return serviceComponent;
    }


}
