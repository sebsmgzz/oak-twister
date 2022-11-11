package com.oaktwister.app.views;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class Controller<T extends Parent> {

    private T node;

    public Controller() {
    }

    public T getNode() {
        if(node == null) {
            node = instantiate();
            initialize(node);
        }
        return node;
    }

    protected abstract T instantiate();

    protected void initialize(T node) {
    }

    public void configStage(Stage stage) {
    }

}
