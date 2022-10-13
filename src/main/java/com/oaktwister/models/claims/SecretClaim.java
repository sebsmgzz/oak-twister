package com.oaktwister.models.claims;

public class SecretClaim extends SimpleClaim<String> {

    private final String hint;

    public String getHint() {
        return hint;
    }

    public SecretClaim(String name, String value, String hint) {
        super(name, value);
        this.hint = hint;
    }

}
