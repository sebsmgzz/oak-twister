package com.oaktwister.models.aggregators.grants;

public class NumberGrant extends Grant<Number> {

    public NumberGrant(String name, Number value) {
        super(name, value);
    }

    public NumberGrant(String name) {
        super(name);
    }

}
