package com.oaktwister.app.views;

import com.oaktwister.app.utils.Lazy;
import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class Controller<T extends Parent> {

    protected final Lazy<T> node;

    public Controller() {
        node = new Lazy<>(this::initializeNode);
    }

    protected abstract T initializeNode();

    public Parent getNode() {
        return node.value();
    }

    public void configStage(Stage stage) {
    }

}
