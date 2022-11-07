package com.oaktwister.app.utils;

import java.util.concurrent.Callable;

public class Lazy<T> {

    private final Callable<T> factory;
    private T instance;

    public Lazy(Callable<T> factory) {
        this.factory = factory;
    }

    public Lazy(T instance) {
        this.instance = instance;
        this.factory = null;
    }

    public boolean isCreated() {
        return instance != null;
    }

    public T value() {
        if(!isCreated()) {
            try {
                assert factory != null;
                instance = factory.call();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return instance;
    }

}
