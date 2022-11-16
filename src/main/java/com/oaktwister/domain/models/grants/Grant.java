package com.oaktwister.domain.models.grants;

import com.oaktwister.domain.seedwork.ValueObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Grant<T> extends ValueObject {

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

    @Override
    protected List<Object> getComponents() {
        ArrayList<Object> components = new ArrayList<>();
        components.add(name);
        components.add(value);
        return components;
    }

}
