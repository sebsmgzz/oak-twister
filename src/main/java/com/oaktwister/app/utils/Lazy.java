package com.oaktwister.app.utils;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class Lazy<T> {

    private final ArrayList<Consumer<T>> listeners;
    private final Callable<T> factory;
    private T instance;
    private boolean creating;

    private Lazy(T instance, Callable<T> factory) {
        this.factory = factory;
        this.instance = instance;
        listeners = new ArrayList<>();
    }

    public Lazy(Callable<T> factory) {
        this(null, factory);
    }

    public Lazy(T instance) {
        this(instance, null);
    }

    public boolean isCreated() {
        return instance != null;
    }

    public boolean isCreating() {
        return creating;
    }

    public T value() {
        if(!isCreated()) {
            instance = create();
        }
        return instance;
    }

    private T create() {
        try {
            assert factory != null;
            creating = true;
            T node = factory.call();
            creating = false;
            for (Consumer<T> listener : listeners) {
                listener.accept(node);
            }
            return node;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean addListener(Consumer<T> listener) {
        return listeners.add(listener);
    }

    public boolean removeListener(Consumer<T> listener) {
        return listeners.remove(listener);
    }

}
