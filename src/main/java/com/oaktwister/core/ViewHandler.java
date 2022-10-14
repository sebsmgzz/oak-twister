package com.oaktwister.core;

import com.oaktwister.services.Resources;
import com.oaktwister.views.roots.LandingViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class ViewHandler {

    private final Stage primaryStage;
    private final ViewFactory viewFactory;
    private final ControllerFactory controllerFactory;

    public ViewHandler(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.viewFactory = new ViewFactory(viewModelFactory);
        this.controllerFactory = new ControllerFactory(this, viewModelFactory);
    }

    public ViewFactory viewLayoutsFactory() {
        return viewFactory;
    }

    public <T extends Parent> T getRootView(@NotNull Class<?> resourceClass, String viewLocation) throws IOException {
        URL resourceUrl = resourceClass.getResource(viewLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setControllerFactory(controllerFactory);
        return fxmlLoader.load();
    }

    public void showLandingView() throws IOException {
        Parent view = getRootView(LandingViewController.class, Resources.Views.Roots.LANDING);
        Scene scene = new Scene(view);
        primaryStage.setTitle(Resources.Strings.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() throws IOException {
        Parent view = getRootView(LandingViewController.class, Resources.Views.Roots.MAIN);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
