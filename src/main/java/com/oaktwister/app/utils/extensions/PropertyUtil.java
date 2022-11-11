package com.oaktwister.app.utils.extensions;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;

public class PropertyUtil {

    public static <T extends Event> boolean tryHandle(
            SimpleObjectProperty<EventHandler<T>> onSelectedActionProperty, T event) {
        EventHandler<T> eventHandler = onSelectedActionProperty.get();
        if(eventHandler == null) {
            return false;
        }
        eventHandler.handle(event);
        return true;
    }

}
