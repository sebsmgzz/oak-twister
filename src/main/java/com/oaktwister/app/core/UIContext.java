package com.oaktwister.app.core;

import javafx.stage.Stage;

public class UIContext {

    private final ViewModelFactory viewModelFactory;
    private final Navigation navigation;

    public UIContext(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        navigation = new Navigation(primaryStage, this);
    }

    public ViewModelFactory viewModels() {
        return viewModelFactory;
    }

    public Navigation navigation() {
        return navigation;
    }

}
