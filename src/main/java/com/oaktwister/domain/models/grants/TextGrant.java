package com.oaktwister.domain.models.grants;

public class TextGrant extends Grant<String> {

    public TextGrant(String name, String value) {
        super(name, value);
    }

    public TextGrant(String name) {
        super(name);
    }

}