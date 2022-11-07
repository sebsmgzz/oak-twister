package com.oaktwister.domain.models.grants;

public class FlagGrant extends Grant<Boolean> {

    public FlagGrant(String name, Boolean value) {
        super(name, value);
    }

    public FlagGrant(String name) {
        super(name);
    }

}
