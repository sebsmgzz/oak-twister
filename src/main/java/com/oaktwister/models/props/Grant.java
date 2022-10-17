package com.oaktwister.models.props;

public abstract class Grant<T> {

    private String name;
    private T value;

    public Grant(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Grant(String name) {
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
