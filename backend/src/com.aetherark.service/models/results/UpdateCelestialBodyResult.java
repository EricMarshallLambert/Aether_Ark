package com.aetherark.service.models.results;

import com.aetherark.service.models.CelestialBodyModel;

import java.util.Objects;

public class UpdateCelestialBodyResult {

    private CelestialBodyModel celestialBody;

    public UpdateCelestialBodyResult(Builder builder) {
        this.celestialBody = builder.celestialBody;
    }

    public CelestialBodyModel getCelestialBody() {
        return celestialBody;
    }

    public void setCelestialBody(CelestialBodyModel celestialBody) {
        this.celestialBody = celestialBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCelestialBodyResult that = (UpdateCelestialBodyResult) o;
        return Objects.equals(celestialBody, that.celestialBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celestialBody);
    }

    @Override
    public String toString() {
        return "UpdateCelestialBodyResult{" +
                "celestialBody=" + celestialBody +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private CelestialBodyModel celestialBody;

        public Builder withCelestialBody(CelestialBodyModel celestialBodyToUse) {
            this.celestialBody = celestialBodyToUse;
            return this;
        }

        public UpdateCelestialBodyResult build() {
            return new UpdateCelestialBodyResult(this);
        }
    }
}

