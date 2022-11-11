package com.oaktwister.app.events;

import javafx.event.Event;
import javafx.event.EventType;

public abstract class ControlEvent<C, E extends Event> extends Event {

    private final C sender;
    private final E innerEvent;

    public ControlEvent(C sender, E innerEvent, EventType<? extends Event> eventType) {
        super(eventType);
        this.sender = sender;
        this.innerEvent = innerEvent;
    }

    public C getSender() {
        return sender;
    }

    public E getInnerEvent() {
        return innerEvent;
    }

}
