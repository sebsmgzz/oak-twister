package com.oaktwister.app.core;

import javafx.stage.Stage;

public class UIContext {

    private final ServiceFactory serviceFactory;
    private final ViewModelFactory viewModelFactory;
    private final Navigation navigation;
    private final StageFactory stageFactory;

    public UIContext(Stage primaryStage) {
        serviceFactory = new ServiceFactory();
        viewModelFactory = new ViewModelFactory(serviceFactory);
        navigation = new Navigation(primaryStage, this);
        stageFactory = new StageFactory(primaryStage);
    }

    public ServiceFactory services() {
        return serviceFactory;
    }

    public ViewModelFactory viewModels() {
        return viewModelFactory;
    }

    public Navigation navigation() {
        return navigation;
    }

    public StageFactory stages() {
        return stageFactory;
    }

}
