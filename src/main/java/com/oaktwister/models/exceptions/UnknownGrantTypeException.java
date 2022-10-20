package com.oaktwister.models.exceptions;

import com.oaktwister.models.aggregators.grants.Grant;

public class UnknownGrantTypeException extends Exception {

    public UnknownGrantTypeException() {
        super("An unrecognized grant type was found");
    }

    public UnknownGrantTypeException(String grantTypeName) {
        super(String.format(
           "No matching grant found for type %s",
            grantTypeName
        ));
    }

    public UnknownGrantTypeException(Class<? extends Grant<?>> grantType) {
        this(grantType.getTypeName());
    }

}
