package com.aetherark.service.models.results;

import com.aetherark.service.models.SolarSystemModel;

public class DeleteSolarSystemResult {
    private SolarSystemModel solarSystemModel;

    public DeleteSolarSystemResult(Builder builder) {
        this.solarSystemModel = builder.solarSystemModel;
    }

    public SolarSystemModel getSolarSystemModel() {
        return solarSystemModel;
    }

    public void setSolarSystemModel(SolarSystemModel solarSystemModel) {
        this.solarSystemModel = solarSystemModel;
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

        public DeleteSolarSystemResult build() {
            return new DeleteSolarSystemResult(this);
        }


    }
}
