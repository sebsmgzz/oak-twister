package com.oaktwister.core;

import javafx.application.Application;
import javafx.stage.Stage;

public class OakTwisterApp extends Application {

    private final ViewModelFactory viewModelFactory;

    public OakTwisterApp() {
        ServiceFactory serviceFactory = new ServiceFactory();
        viewModelFactory = new ViewModelFactory(serviceFactory);
    }

    @Override
    public void start(Stage stage) {
        UIContext ui = new UIContext(stage, viewModelFactory);
        ui.navigation().goToLogin();
    }

}