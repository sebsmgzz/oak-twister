package com.oaktwister.core;

import com.oaktwister.controllers.controls.AccountsController;
import com.oaktwister.controllers.controls.IdentitiesController;
import com.oaktwister.controllers.controls.PlatformsController;
import com.oaktwister.controllers.dialogs.EditPlatformController;
import com.oaktwister.controllers.dialogs.LoginFailedController;
import com.oaktwister.controllers.layouts.LoginController;
import com.oaktwister.controllers.layouts.MainController;
import com.oaktwister.utils.Lazy;
import com.oaktwister.utils.extensions.NodeUtil;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.views.platforms.EditPlatformDialog;
import com.oaktwister.views.login.LoginFailedAlert;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerFactory {

    private final Stage primaryStage;
    private final UIContext ui;

    private final Lazy<MainController> mainController;
    private final Lazy<LoginController> loginController;
    private final Lazy<AccountsController> accountsController;
    private final Lazy<PlatformsController> platformsController;
    private final Lazy<IdentitiesController> identitiesController;

    public ControllerFactory(Stage primaryStage, UIContext ui) {
        this.primaryStage = primaryStage;
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

    public EditPlatformController editPlatform() {
        Stage stage = NodeUtil.getDialogStage(primaryStage);
        EditPlatformController controller = new EditPlatformController(stage);
        EditPlatformDialog view = controller.getView();
        Scene scene = new Scene(view);
        stage.setScene(scene);
        return controller;
    }

    public EditPlatformController editPlatform(PlatformViewModel viewModel) {
        EditPlatformController controller = editPlatform();
        controller.setPlatform(viewModel);
        return controller;
    }

    public LoginFailedController loginFailed() {
        Stage stage = NodeUtil.getDialogStage(primaryStage);
        LoginFailedController controller = new LoginFailedController(stage);
        controller.initialize();
        LoginFailedAlert view = controller.getView();
        Scene scene = new Scene(view);
        stage.setScene(scene);
        return controller;
    }

}
