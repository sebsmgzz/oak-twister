package com.oaktwister.core;

import com.oaktwister.services.Resources;
import com.oaktwister.views.roots.LandingViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private final ControllerFactory controllerFactory;
    private final Stage primaryStage;

    public ViewFactory(Stage primaryStage, ViewModelFactory viewModelFactory) {
        this.primaryStage = primaryStage;
        this.controllerFactory = new ControllerFactory(this, viewModelFactory);
    }

    public <T extends Node> T getView(@NotNull Class<?> resourceClass, String viewLocation) throws IOException {
        URL resourceUrl = resourceClass.getResource(viewLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(resourceUrl);
        fxmlLoader.setControllerFactory(controllerFactory);
        return fxmlLoader.load();
    }

    public void showLandingView() throws IOException {
        Parent view = getView(LandingViewController.class, Resources.Views.Roots.LANDING);
        Scene scene = new Scene(view);
        primaryStage.setTitle(Resources.Strings.App.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainView() throws IOException {
        Parent view = getView(LandingViewController.class, Resources.Views.Roots.MAIN);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
