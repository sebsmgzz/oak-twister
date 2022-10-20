package com.oaktwister.models.aggregators.grants;

public class FlagGrant extends Grant<Boolean> {

    public FlagGrant(String name, Boolean value) {
        super(name, value);
    }

    public FlagGrant(String name) {
        super(name);
    }

}
