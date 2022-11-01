package com.oaktwister.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ControlEvent<C, E extends Event> extends Event {

    private final C sender;
    private final E innerEvent;

    public ControlEvent(C sender, E innerEvent) {
        super(new EventType<>(Event.ANY, "CONTROL_EVENT"));
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
