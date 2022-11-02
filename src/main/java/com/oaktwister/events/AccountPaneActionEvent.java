package com.oaktwister.events;

import com.oaktwister.views.accounts.AccountPane;
import javafx.event.ActionEvent;

public class AccountPaneActionEvent extends ControlEvent<AccountPane, ActionEvent> {

    public AccountPaneActionEvent(AccountPane sender, ActionEvent innerEvent) {
        super(sender, innerEvent);
    }

}