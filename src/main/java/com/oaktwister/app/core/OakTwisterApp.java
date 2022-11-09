package com.oaktwister.app.core;

import javafx.application.Application;
import javafx.stage.Stage;

public class OakTwisterApp extends Application {

    @Override
    public void start(Stage stage) {
        UIContext ui = new UIContext(stage);
        ui.navigation().goToLogin();
    }

}