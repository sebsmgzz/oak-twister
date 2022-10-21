package com.oaktwister.events;

import javafx.event.Event;
import javafx.event.EventType;

public abstract class CancellableEvent extends Event {

    private boolean canceled;

    public CancellableEvent(EventType<? extends Event> eventType) {
        super(eventType);
        canceled = false;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
