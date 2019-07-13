package com.se231.onlineedu.exception;


public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5861310537366287163L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
    }
}
