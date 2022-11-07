package com.oaktwister.app.events;

import com.oaktwister.app.views.identities.IdentityPane;
import javafx.event.ActionEvent;

public class IdentityPaneActionEvent extends ControlEvent<IdentityPane, ActionEvent> {

    public IdentityPaneActionEvent(IdentityPane sender, ActionEvent innerEvent) {
        super(sender, innerEvent);
    }

}
