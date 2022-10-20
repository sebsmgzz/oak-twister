package com.oaktwister.views.events;

import com.oaktwister.views.View;
import javafx.event.Event;

public class ViewEvent<T extends Event, U extends View, V> extends WrapperEvent<T> {

    private final U sender;
    private final V args;

    public ViewEvent(T internalEvent, U sender, V args) {
        super(internalEvent);
        this.sender = sender;
        this.args = args;
    }

    public ViewEvent(T internalEvent, U sender) {
        this(internalEvent, sender, null);
    }

    public U getSender() {
        return sender;
    }

    public V getArgs() {
        return args;
    }

}
