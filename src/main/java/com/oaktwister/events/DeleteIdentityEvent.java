package com.oaktwister.events;

import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.event.Event;
import javafx.event.EventType;

public class DeleteIdentityEvent extends Event {

    private static final EventType<DeleteIdentityEvent> DELETING_IDENTITY =
            new EventType<>(Event.ANY, "DELETING_IDENTITY");

    private final IdentityViewModel identityViewModel;
    private boolean canceled;

    public DeleteIdentityEvent(IdentityViewModel identityViewModel) {
        super(DELETING_IDENTITY);
        this.identityViewModel = identityViewModel;
        canceled = false;
    }

    public IdentityViewModel getIdentityViewModel() {
        return identityViewModel;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}
