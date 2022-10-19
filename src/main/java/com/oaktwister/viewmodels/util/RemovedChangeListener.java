package com.oaktwister.viewmodels.util;

import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.function.Consumer;

public class RemovedChangeListener<T> implements ListChangeListener<T> {

    private final Consumer<T> consumer;

    public RemovedChangeListener(Consumer<T> consumer) {
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
