package com.oaktwister.app.events;

import com.oaktwister.app.views.widgets.crud.CrudPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

public class CrudPaneActionEvent<T extends Node> extends ControlEvent<CrudPane<T>, ActionEvent> {

    public static final EventType<CrudPaneActionEvent<?>> CRUD_PANE_ACTION =
            new EventType<>(Event.ANY, "CRUD_PANE_ACTION");

    public CrudPaneActionEvent(CrudPane<T> sender, ActionEvent innerEvent) {
        super(sender, innerEvent, CRUD_PANE_ACTION);
    }

}
