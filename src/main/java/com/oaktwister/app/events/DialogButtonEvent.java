package com.oaktwister.app.events;

import com.oaktwister.app.views.widgets.dialogs.DialogButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;

public class DialogButtonEvent extends ControlEvent<DialogButton, ActionEvent> {

    public static final EventType<DialogButtonEvent> DIALOG_BUTTON_EVENT =
            new EventType<>(Event.ANY, "DIALOG_BUTTON_EVENT");

    public DialogButtonEvent(DialogButton sender, ActionEvent innerEvent) {
        super(sender, innerEvent, DIALOG_BUTTON_EVENT);
    }

}
