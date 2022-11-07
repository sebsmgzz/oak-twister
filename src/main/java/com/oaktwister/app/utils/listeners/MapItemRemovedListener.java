package com.oaktwister.app.utils.listeners;

import javafx.collections.MapChangeListener;
import java.util.function.BiConsumer;

public class MapItemRemovedListener<K, V> implements MapChangeListener<K, V> {

    private final BiConsumer<K, V> consumer;

    public MapItemRemovedListener(BiConsumer<K, V> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends K, ? extends V> change) {
        if(change.wasRemoved()) {
            K key = change.getKey();
            V value = change.getValueRemoved();
            consumer.accept(key, value);
        }
    }

}
