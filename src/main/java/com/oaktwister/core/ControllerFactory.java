package com.oaktwister.core;

import com.oaktwister.controllers.controls.AccountsController;
import com.oaktwister.controllers.controls.IdentitiesController;
import com.oaktwister.controllers.controls.PlatformsController;
import com.oaktwister.controllers.layouts.LoginController;
import com.oaktwister.controllers.layouts.MainController;

public class ControllerFactory {

    private final UIContext ui;

    private final Lazy<MainController> mainController;
    private final Lazy<LoginController> loginController;
    private final Lazy<AccountsController> accountsController;
    private final Lazy<PlatformsController> platformsController;
    private final Lazy<IdentitiesController> identitiesController;

    public ControllerFactory(UIContext ui) {
        this.ui = ui;
        mainController = new Lazy<>(this::getMainController);
        loginController = new Lazy<>(this::getLoginController);
        accountsController = new Lazy<>(this::getAccountsController);
        platformsController = new Lazy<>(this::getPlatformsController);
        identitiesController = new Lazy<>(this::getIdentitiesController);
    }

    private MainController getMainController() {
        MainController controller = new MainController(ui);
        controller.initialize();
        return controller;
    }

    public MainController main() {
        return mainController.value();
    }

    private LoginController getLoginController() {
        LoginController controller = new LoginController(ui);
        controller.initialize();
        return controller;
    }

    public LoginController login() {
        return loginController.value();
    }

    private AccountsController getAccountsController() {
        AccountsController accountsController = new AccountsController(ui);
        accountsController.initialize();
        return accountsController;
    }

    public AccountsController accounts() {
        return accountsController.value();
    }

    private PlatformsController getPlatformsController() {
        PlatformsController platformsController = new PlatformsController(ui);
        platformsController.initialize();
        return platformsController;
    }

    public PlatformsController platforms() {
        return platformsController.value();
    }

    private IdentitiesController getIdentitiesController() {
        IdentitiesController identitiesController = new IdentitiesController(ui);
        identitiesController.initialize();
        return identitiesController;
    }

    public IdentitiesController identities() {
        return identitiesController.value();
    }

}
