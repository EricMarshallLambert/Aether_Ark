package com.aetherark.service.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 8891222990065152474L;

    /**
     * Exception with no message or cause.
     */
    public UserNameAlreadyExistsException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UserNameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UserNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

