package com.oaktwister.viewmodels.landing;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LandingViewModel {

    public static final String MESSAGE = "Welcome to JavaFX Application!";
    private final StringProperty message;

    public StringProperty getMessage() {
        return message;
    }

    public LandingViewModel() {
        message = new SimpleStringProperty();
    }

    public void greet() {
        message.set(MESSAGE);
    }

}
