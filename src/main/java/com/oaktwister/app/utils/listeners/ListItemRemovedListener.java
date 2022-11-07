package com.oaktwister.app.utils.listeners;

import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.function.Consumer;

public class ListItemRemovedListener<T> implements ListChangeListener<T> {

    private final Consumer<T> consumer;

    public ListItemRemovedListener(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends T> change) {
        while(change.next()) {
            if(change.wasRemoved()) {
                List<? extends T> changedRange = change.getRemoved();
                for (T entity : changedRange) {
                    consumer.accept(entity);
                }
            }
        }
    }

}
