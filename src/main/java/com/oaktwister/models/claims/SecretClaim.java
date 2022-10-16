package com.oaktwister.models.claims;

public class SecretClaim extends Claim<String> {

    private String hint;

    public SecretClaim(String name, String value, String hint) {
        super(name, value);
        this.hint = hint;
    }

    public SecretClaim(String name) {
        super(name);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
