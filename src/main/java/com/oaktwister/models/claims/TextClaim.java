package com.oaktwister.models.claims;

public class TextClaim extends Claim<String> {

    public TextClaim(String name, String value) {
        super(name, value);
    }

    public TextClaim(String name) {
        super(name);
    }

}
