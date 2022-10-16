package com.oaktwister.models.claims;

public class FlagClaim extends Claim<Boolean> {

    public FlagClaim(String name, Boolean value) {
        super(name, value);
    }

    public FlagClaim(String name) {
        super(name);
    }

}
