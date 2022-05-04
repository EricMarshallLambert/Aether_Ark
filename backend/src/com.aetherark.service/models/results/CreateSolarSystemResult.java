package com.aetherark.service.models.results;

import com.aetherark.service.models.SolarSystemModel;

public class CreateSolarSystemResult {
    private SolarSystemModel solarSystemModel;

    public CreateSolarSystemResult(Builder builder) {
        this.solarSystemModel = builder.solarSystemModel;

    }

    public SolarSystemModel getSolarSystemModel() {
        return solarSystemModel;
    }

    public void setSolarSystemModel(SolarSystemModel model) {
        this.solarSystemModel = model;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private SolarSystemModel solarSystemModel;


        public Builder withSolarSystemModel(SolarSystemModel model) {
            this.solarSystemModel = model;
            return this;
        }

        public CreateSolarSystemResult build() {
            return new CreateSolarSystemResult(this);
        }

    }
}
