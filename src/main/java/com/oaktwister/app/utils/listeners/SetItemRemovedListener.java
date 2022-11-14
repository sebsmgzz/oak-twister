package com.oaktwister.app.utils.listeners;

import javafx.collections.SetChangeListener;

import java.util.function.Consumer;

public class SetItemRemovedListener<T> implements SetChangeListener<T> {

    private final Consumer<T> consumer;

    public SetItemRemovedListener(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends T> change) {
        if(change.wasRemoved()) {
            T elementRemoved = change.getElementRemoved();
            consumer.accept(elementRemoved);
        }
    }

}
