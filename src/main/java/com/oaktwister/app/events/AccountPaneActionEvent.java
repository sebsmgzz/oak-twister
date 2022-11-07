package com.oaktwister.app.events;

import com.oaktwister.app.views.accounts.AccountPane;
import javafx.event.ActionEvent;

public class AccountPaneActionEvent extends ControlEvent<AccountPane, ActionEvent> {

    public AccountPaneActionEvent(AccountPane sender, ActionEvent innerEvent) {
        super(sender, innerEvent);
    }

}
