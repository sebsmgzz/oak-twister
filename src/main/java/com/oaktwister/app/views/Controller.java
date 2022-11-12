package com.oaktwister.app.views;

import javafx.scene.Parent;

public abstract class Controller<T extends Parent> {

    private T root;

    public T getRoot() {
        if(root == null) {
            root = initialize();
        }
        return root;
    }

    protected abstract T initialize();


}
