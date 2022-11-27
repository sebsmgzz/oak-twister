package com.oaktwister.app.core;

import com.oaktwister.app.views.login.LoginController;
import com.oaktwister.app.views.main.MainController;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    private final Stage primaryStage;

    private final LoginController loginController;
    private final MainController mainController;

    public Navigation(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        loginController = new LoginController(ui);
        mainController = new MainController(ui);
    }

    public void goToLogin() {
        Parent node = loginController.getRoot();
        Scene scene = new Scene(node);
        loginController.configStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goToMain() {
        Parent node = mainController.getRoot();
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
