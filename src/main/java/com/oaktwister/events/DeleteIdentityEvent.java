package com.oaktwister.events;

import com.oaktwister.viewmodels.models.IdentityViewModel;
import javafx.event.Event;
import javafx.event.EventType;

public class DeleteIdentityEvent extends CancellableEvent {

    private static final EventType<DeleteIdentityEvent> DELETE_IDENTITY =
            new EventType<>(Event.ANY, "DELETE_IDENTITY");

    private final IdentityViewModel identityViewModel;

    public DeleteIdentityEvent(IdentityViewModel identityViewModel) {
        super(DELETE_IDENTITY);
        this.identityViewModel = identityViewModel;
    }

    public IdentityViewModel getIdentityViewModel() {
        return identityViewModel;
    }

}
