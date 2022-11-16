package com.oaktwister.domain.seedwork;

import org.jetbrains.annotations.NotNull;

public abstract class Enumerable<K, V> {

    private final K key;
    private final V value;

    protected Enumerable(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean equals(Enumerable<?, ?> enumerable) {
        boolean equalKeyClass = key.getClass() == enumerable.key.getClass();
        boolean valueKeyClass = value.getClass() == enumerable.value.getClass();
        if(equalKeyClass && valueKeyClass) {
            return enumerable.getKey() == getKey();
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Enumerable<?, ?> enumerable) {
            return equals(enumerable);
        }
        return false;
    }
}
