package com.aetherark.service.models.results;

import com.aetherark.service.models.CelestialBodyModel;

import java.util.Objects;

public class DeleteCelestialBodyResult {

    private CelestialBodyModel celestialBody;

    public DeleteCelestialBodyResult(Builder builder) {
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
        DeleteCelestialBodyResult that = (DeleteCelestialBodyResult) o;
        return Objects.equals(celestialBody, that.celestialBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celestialBody);
    }

    @Override
    public String toString() {
        return "DeleteCelestialBodyResult{" +
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

        public DeleteCelestialBodyResult build() {
            return new DeleteCelestialBodyResult(this);
        }
    }
}
