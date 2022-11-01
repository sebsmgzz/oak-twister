package com.oaktwister.controllers.dialogs;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.services.resources.ViewResources;

import com.oaktwister.views.dialogs.LoginFailedAlert;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFailedController {

    private final Stage stage;

    private final LoginFailedAlert view;

    public LoginFailedController(Stage stage) {
        this.stage = stage;
        this.view = new LoginFailedAlert();
    }

    public void initialize() {
        view.resultProperty().addListener((observable, oldValue, newValue) -> stage.close());
    }

    public LoginFailedAlert getView() {
        return view;
    }

    public void setMessage(String message) {
        view.messageProperty().set(message);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

}
