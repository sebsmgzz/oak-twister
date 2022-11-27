package com.oaktwister.app.core;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StageFactory {

    private final Stage primaryStage;

    public StageFactory(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getDialogStage(Parent node) {
        Scene scene = new Scene(node);
        return getDialogStage(scene);
    }

    public Stage getDialogStage(Scene scene) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage.getScene().getWindow());
        stage.setScene(scene);
        return stage;
    }

}
