package com.oaktwister.models.claims;

public class SimpleClaim<T> implements Claim<T> {

    private final String name;
    private final T value;

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public SimpleClaim(String name, T value) {
        this.name = name;
        this.value = value;
    }

}
