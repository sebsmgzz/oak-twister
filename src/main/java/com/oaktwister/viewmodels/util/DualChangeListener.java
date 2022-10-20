package com.oaktwister.viewmodels.util;

import javafx.collections.ListChangeListener;

import java.util.List;
import java.util.function.Consumer;

public class DualChangeListener<T> implements ListChangeListener<T> {

    private final Consumer<T> onAdded;
    private final Consumer<T> onRemoved;

    public DualChangeListener(Consumer<T> onAdded, Consumer<T> onRemoved) {
        this.onAdded = onAdded;
        this.onRemoved = onRemoved;
    }

    @Override
    public void onChanged(Change<? extends T> change) {
        while(change.next()) {
            if(change.wasAdded()) {
                List<? extends T> changedRange = change.getAddedSubList();
                for (T entity : changedRange) {
                    onAdded.accept(entity);
                }
            }
            if(change.wasRemoved()) {
                List<? extends T> changedRange = change.getRemoved();
                for (T entity : changedRange) {
                    onRemoved.accept(entity);
                }
            }
        }
    }

}