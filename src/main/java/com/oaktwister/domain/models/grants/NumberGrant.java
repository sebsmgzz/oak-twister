package com.oaktwister.domain.models.grants;

public class NumberGrant extends Grant<Number> {

    public NumberGrant(String name, Number value) {
        super(name, value);
    }

    public NumberGrant(String name) {
        super(name);
    }

}
