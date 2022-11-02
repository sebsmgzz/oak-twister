package com.oaktwister.controllers.dialogs;

import com.oaktwister.views.login.LoginFailedAlert;
import javafx.stage.Stage;

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
