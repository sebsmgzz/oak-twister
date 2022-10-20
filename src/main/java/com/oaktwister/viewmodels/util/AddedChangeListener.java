package com.oaktwister.viewmodels.util;

import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.function.Consumer;

public class AddedChangeListener<T> implements ListChangeListener<T> {

    private final Consumer<T> consumer;

    public AddedChangeListener(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends T> change) {
        while(change.next()) {
            if(change.wasAdded()) {
                List<? extends T> changedRange = change.getAddedSubList();
                for (T entity : changedRange) {
                    consumer.accept(entity);
                }
            }
        }
    }

}