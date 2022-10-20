package com.oaktwister.views.events;

import javafx.event.Event;

public class WrapperEvent<T extends Event> extends Event {

    private final T internalEvent;

    public WrapperEvent(T internalEvent) {
        super(internalEvent.getEventType());
        this.internalEvent = internalEvent;
    }

    public T getInternalEvent() {
        return internalEvent;
    }

}
