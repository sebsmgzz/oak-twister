package com.oaktwister.app.utils.extensions;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class PropertyUtil {

    public static <T extends Event> boolean tryHandle(
            ObjectProperty<EventHandler<T>> onSelectedActionProperty, T event) {
        EventHandler<T> eventHandler = onSelectedActionProperty.get();
        if(eventHandler == null) {
            return false;
        }
        eventHandler.handle(event);
        return true;
    }

    public static boolean tryClose(ObjectProperty<Stage> stageProperty) {
        Stage stage = stageProperty.get();
        if(stage == null) {
            return false;
        }
        stage.close();
        return true;
    }

}
