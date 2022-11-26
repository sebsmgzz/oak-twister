package com.oaktwister.domain.exceptions;

public class InvalidSessionPropertyException extends Exception {

    public InvalidSessionPropertyException() {
        super("A session property must be set before it can be accessed");
    }

    public InvalidSessionPropertyException(String propertyName) {
        super(String.format("The session property '%s', must be set before it can be accessed", propertyName));
    }

}
