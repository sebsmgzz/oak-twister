package com.oaktwister.core;

import com.oaktwister.services.resources.ImageResources;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.controllers.layouts.LoginController;
import com.oaktwister.controllers.layouts.MainController;
import com.oaktwister.views.layouts.MainLayout;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Navigation {

    private final Stage primaryStage;
    private final UIContext ui;

    public Navigation(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
        this.ui = ui;
    }

    public void goToLogin() {
        LoginController controller = ui.controllers().login();
        Parent view = controller.getView();
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        primaryStage.setTitle(StringResources.App.TITLE);
        primaryStage.show();
    }

    public void goToMain() {
        MainController controller = ui.controllers().main();
        MainLayout view = controller.getView();
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
