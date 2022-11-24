package com.oaktwister.domain.exceptions;

public class UnknownMetaGrantException extends Exception {

    public UnknownMetaGrantException() {
        super("Unknown meta grant");
    }

    public UnknownMetaGrantException(String metaGrantName) {
        super(String.format("Unknown meta grant %s", metaGrantName));
    }

}
