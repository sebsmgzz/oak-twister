package com.oaktwister.app.utils.listeners;

import javafx.collections.SetChangeListener;

import java.util.function.Consumer;

public class SetItemAddedListener<T> implements SetChangeListener<T> {

    private final Consumer<T> consumer;

    public SetItemAddedListener(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends T> change) {
        if(change.wasAdded()) {
            T elementAdded = change.getElementAdded();
            consumer.accept(elementAdded);
        }
    }

}
