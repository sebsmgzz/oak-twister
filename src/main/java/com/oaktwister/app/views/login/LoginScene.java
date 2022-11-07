package com.oaktwister.app.views.login;

import javafx.scene.Scene;

public class LoginScene extends Scene {

    private final LoginLayout loginLayout;

    public LoginScene() {
        super(new LoginLayout());
        loginLayout = (LoginLayout) getRoot();
    }

}
