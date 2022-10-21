package com.oaktwister.events;

import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import javafx.event.Event;
import javafx.event.EventType;

public class DeletePlatformEvent extends CancellableEvent {

    private static final EventType<DeletePlatformEvent> DELETE_PLATFORM =
            new EventType<>(Event.ANY, "DELETE_PLATFORM");

    private final PlatformViewModel platformViewModel;

    public DeletePlatformEvent(PlatformViewModel platformViewModel) {
        super(DELETE_PLATFORM);
        this.platformViewModel = platformViewModel;
    }

    public PlatformViewModel getPlatformViewModel() {
        return platformViewModel;
    }

}
