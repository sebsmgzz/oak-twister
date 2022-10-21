package com.oaktwister.events;

import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.event.Event;
import javafx.event.EventType;

public class DeleteAccountEvent extends CancellableEvent {

    private static final EventType<DeleteAccountEvent> DELETE_ACCOUNT =
            new EventType<>(Event.ANY, "DELETE_ACCOUNT");

    private final AccountViewModel accountViewModel;

    public DeleteAccountEvent(AccountViewModel accountViewModel) {
        super(DELETE_ACCOUNT);
        this.accountViewModel = accountViewModel;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

}
