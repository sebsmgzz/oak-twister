package com.oaktwister.app.events;

import com.oaktwister.app.views.identities.IdentityPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;

public class IdentityPaneActionEvent extends ControlEvent<IdentityPane, ActionEvent> {

    public static final EventType<IdentityPaneActionEvent> IDENTITY_PANE_ACTION =
            new EventType<>(Event.ANY, "IDENTITY_PANE_ACTION");

    public IdentityPaneActionEvent(IdentityPane sender, ActionEvent innerEvent) {
        super(sender, innerEvent, IDENTITY_PANE_ACTION);
    }

}
