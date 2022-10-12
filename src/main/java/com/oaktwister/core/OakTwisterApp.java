package com.oaktwister.core;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class OakTwisterApp extends Application {

    private final ViewModelFactory viewModelFactory;

    public OakTwisterApp() {
        viewModelFactory = new ViewModelFactory();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory(stage, viewModelFactory);
        viewFactory.showLandingView();
    }

}