package com.oaktwister.models.claims;

public class NumberClaim extends Claim<Number> {

    public NumberClaim(String name, Number value) {
        super(name, value);
    }

    public NumberClaim(String name) {
        super(name);
    }

}
