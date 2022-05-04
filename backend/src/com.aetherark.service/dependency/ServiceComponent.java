package com.aetherark.service.dependency;

import com.aetherark.service.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {MapperModule.class} )

public interface ServiceComponent {

    CreateSolarSystemActivity provideCreateSolarSystemActivity();
    GetSolarSystemActivity provideGetSolarSystemActivity();
    UpdateSolarSystemActivity provideUpdateSolarSystemActivity();
    DeleteSolarSystemActivity provideDeleteSolarSystemActivity();

    CreateUserActivity provideCreateUserActivity();
    GetUserActivity provideGetUserActivity();
    UpdateUserActivity provideUpdateUserActivity();
    DeleteUserActivity provideDeleteUserActivity();

    CreateCelestialBodyActivity provideCreateCelestialBodyActivity();
    GetCelestialBodyActivity provideGetCelestialBodyActivity();
    UpdateCelestialBodyActivity provideUpdateCelestialBodyActivity();
    DeleteCelestialBodyActivity provideDeleteCelestialBodyActivity();

}
