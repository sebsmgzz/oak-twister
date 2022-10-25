package com.oaktwister.utils.listeners;

import javafx.collections.MapChangeListener;
import java.util.function.BiConsumer;

public class MapItemAddedListener<K, V> implements MapChangeListener<K, V> {

    private final BiConsumer<K, V> consumer;

    public MapItemAddedListener(BiConsumer<K, V> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onChanged(Change<? extends K, ? extends V> change) {
        if(change.wasAdded()) {
            K key = change.getKey();
            V value = change.getValueAdded();
            consumer.accept(key, value);
        }
    }

}
