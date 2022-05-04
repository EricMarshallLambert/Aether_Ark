package com.aetherark.service.exceptions;

public class SolarSystemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4792107936262979933L;

    /**
     * Exception with no message or cause.
     */
    public SolarSystemNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public SolarSystemNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public SolarSystemNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public SolarSystemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
