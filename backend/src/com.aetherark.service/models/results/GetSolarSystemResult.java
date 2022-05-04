package com.aetherark.service.models.results;

import com.aetherark.service.models.SolarSystemModel;

import java.util.List;

public class GetSolarSystemResult {
    private SolarSystemModel solarSystemModel;
    private List<SolarSystemModel> solarSystemModels;

    public GetSolarSystemResult(Builder builder) {
        this.solarSystemModel = builder.solarSystemModel;
        this.solarSystemModels = builder.solarSystemModels;
    }

    public SolarSystemModel getSolarSystemModel() {
        return solarSystemModel;
    }

    public void setSolarSystemModel(SolarSystemModel solarSystemModel) {
        this.solarSystemModel = solarSystemModel;
    }

    public List<SolarSystemModel> getSolarSystemModels() {
        return solarSystemModels;
    }

    public void setSolarSystemModels(List<SolarSystemModel> solarSystemModels) {
        this.solarSystemModels = solarSystemModels;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private SolarSystemModel solarSystemModel;
        private List<SolarSystemModel> solarSystemModels;


        public Builder withSolarSystemModel(SolarSystemModel model) {
            this.solarSystemModel = model;
            return this;
        }

        public Builder withSolarSystemModels(List<SolarSystemModel> listOfModels) {
            this.solarSystemModels = listOfModels;
            return this;
        }

        public GetSolarSystemResult build() {
            return new GetSolarSystemResult(this);
        }


    }
}


