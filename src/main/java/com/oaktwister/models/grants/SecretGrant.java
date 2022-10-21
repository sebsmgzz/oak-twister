package com.oaktwister.models.grants;

public class SecretGrant extends Grant<String> {

    private String hint;

    public SecretGrant(String name, String value, String hint) {
        super(name, value);
        this.hint = hint;
    }

    public SecretGrant(String name) {
        super(name);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
