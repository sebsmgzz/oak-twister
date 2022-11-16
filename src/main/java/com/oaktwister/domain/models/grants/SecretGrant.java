package com.oaktwister.domain.models.grants;

import java.util.List;

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

    @Override
    protected List<Object> getComponents() {
        List<Object> components = super.getComponents();
        components.add(hint);
        return components;
    }
}
