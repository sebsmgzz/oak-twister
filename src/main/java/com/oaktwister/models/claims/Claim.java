package com.oaktwister.models.claims;

public abstract class Claim<T> {

    private String name;
    private T value;

    public Claim(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Claim(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
