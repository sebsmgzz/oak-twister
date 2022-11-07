package com.oaktwister.app.core;

import com.oaktwister.app.controllers.layouts.LoginController;
import com.oaktwister.app.controllers.layouts.MainController;
import com.oaktwister.app.utils.Lazy;

import javafx.stage.Stage;

public class ControllerFactory {

    private final Stage primaryStage;
    private final UIContext ui;

    private final Lazy<MainController> mainController;
    private final Lazy<LoginController> loginController;

    public ControllerFactory(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        this.ui = ui;
        mainController = new Lazy<>(this::getMainController);
        loginController = new Lazy<>(this::getLoginController);
    }

    private MainController getMainController() {
        MainController controller = new MainController(ui);
        controller.initialize();
        return controller;
    }

    public MainController main() {
        return mainController.value();
    }

    private LoginController getLoginController() {
        LoginController controller = new LoginController(ui);
        controller.initialize();
        return controller;
    }

    public LoginController login() {
        return loginController.value();
    }

}
