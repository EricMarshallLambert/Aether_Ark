package com.aetherark.service.exceptions;

public class CelestialBodyNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -3903407217451957066L;

    /**
     * Exception with no message or cause.
     */
    public CelestialBodyNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CelestialBodyNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CelestialBodyNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CelestialBodyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
