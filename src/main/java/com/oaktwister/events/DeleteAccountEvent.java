package com.oaktwister.events;

import com.oaktwister.viewmodels.models.AccountViewModel;
import javafx.event.Event;
import javafx.event.EventType;

public class DeleteAccountEvent extends Event {

    private static final EventType<DeleteAccountEvent> DELETE_ACCOUNT =
            new EventType<>(Event.ANY, "DELETE_ACCOUNT");

    private final AccountViewModel accountViewModel;
    private boolean canceled;

    public DeleteAccountEvent(AccountViewModel accountViewModel) {
        super(DELETE_ACCOUNT);
        this.accountViewModel = accountViewModel;
        canceled = false;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
