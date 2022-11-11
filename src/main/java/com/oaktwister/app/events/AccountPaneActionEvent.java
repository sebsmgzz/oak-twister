package com.oaktwister.app.events;

import com.oaktwister.app.views.accounts.AccountPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;

public class AccountPaneActionEvent extends ControlEvent<AccountPane, ActionEvent> {

    public static final EventType<AccountPaneActionEvent> ACCOUNT_PANE_ACTION =
            new EventType<>(Event.ANY, "ACCOUNT_PANE_ACTION");

    public AccountPaneActionEvent(AccountPane sender, ActionEvent innerEvent) {
        super(sender, innerEvent, ACCOUNT_PANE_ACTION);
    }

}
