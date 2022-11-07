package com.oaktwister.app.core;

import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.controllers.layouts.LoginController;
import com.oaktwister.app.controllers.layouts.MainController;
import com.oaktwister.app.utils.Lazy;
import com.oaktwister.app.utils.extensions.FXMLUtil;
import com.oaktwister.app.views.main.MainLayout;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Navigation {

    private final Stage primaryStage;
    private final UIContext ui;

    private final Lazy<Scene> loginScene;
    private final Lazy<Scene> mainScene;

    public Navigation(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        this.ui = ui;
        loginScene = new Lazy<>(this::getLoginScene);
        mainScene = new Lazy<>(this::getMainScene);
    }

    private Scene getLoginScene() {
        LoginController controller = ui.controllers().login();
        Parent view = controller.getView();
        return new Scene(view);
    }

    private Scene getMainScene() {
        MainController controller = ui.controllers().main();
        MainLayout view = controller.getView();
        return new Scene(view);
    }

    public void goToLogin() {
        Scene scene = loginScene.value();
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.show();
    }

    public void goToMain() {
        Scene scene = mainScene.value();
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
