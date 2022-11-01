package com.oaktwister.core;

import javafx.stage.Stage;

public class UIContext {

    private final Stage primaryStage;
    private final ViewModelFactory viewModelFactory;
    private final Navigation navigation;
    private final ControllerFactory controllerFactory;

    public UIContext(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewModelFactory = viewModelFactory;
        navigation = new Navigation(primaryStage, this);
        controllerFactory = new ControllerFactory(this);
    }

    public ViewModelFactory viewModels() {
        return viewModelFactory;
    }

    public Navigation navigation() {
        return navigation;
    }

    public ControllerFactory controllers() {
        return controllerFactory;
    }

}
