package com.oaktwister.app.core;

import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.views.login.LoginController;
import com.oaktwister.app.views.main.MainController;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    private final Stage primaryStage;
    private final UIContext ui;

    private final LoginController loginController;
    private final MainController mainController;

    public Navigation(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        this.ui = ui;
        loginController = new LoginController(ui);
        mainController = new MainController(ui);
    }

    public void goToLogin() {
        Parent node = loginController.getNode();
        Scene scene = new Scene(node);
        loginController.configStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goToMain() {
        Parent node = mainController.getNode();
        Scene scene = new Scene(node);
        mainController.configStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getDialogStage(Parent node) {
        Scene scene = new Scene(node);
        Stage stage = FXMLUtil.getDialogStage(primaryStage);
        stage.setScene(scene);
        return stage;
    }

}
