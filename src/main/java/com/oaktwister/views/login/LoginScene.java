package com.oaktwister.views.login;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScene extends Scene {

    private final LoginLayout loginLayout;

    public LoginScene() {
        super(new LoginLayout());
        loginLayout = (LoginLayout) getRoot();
    }

}
