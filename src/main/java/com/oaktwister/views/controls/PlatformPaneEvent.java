package com.oaktwister.views.controls;

import javafx.event.Event;
import javafx.event.EventType;

public class PlatformPaneEvent extends Event {

    private static final EventType<PlatformPaneEvent> PLATFORM_PANE_EVENT =
            new EventType<>(Event.ANY, "PLATFORM_PANE_EVENT");

    private final PlatformPane platformPane;

    public PlatformPaneEvent(PlatformPane platformPane) {
        super(PLATFORM_PANE_EVENT);
        this.platformPane = platformPane;
    }

    public PlatformPane getPlatformPane() {
        return platformPane;
    }

}
