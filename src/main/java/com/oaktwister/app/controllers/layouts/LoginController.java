package com.oaktwister.app.controllers.layouts;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.viewmodels.views.LoginViewModel;
import com.oaktwister.app.views.DialogResult;
import com.oaktwister.app.views.login.LoginFailedAlert;
import com.oaktwister.app.views.login.LoginLayout;
import com.oaktwister.app.views.login.LoginDriveCell;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoginController {

    private final UIContext ui;

    private final LoginViewModel viewModel;
    private final LoginLayout view;

    public LoginController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().login();
        view = new LoginLayout();
    }

    public Parent getView() {
        return view;
    }

    public void initialize() {
        // Set button's actions
        view.onLoginActionProperty().set(this::onLoginButtonClick);
        view.onNewDriveLinkActionProperty().set(this::onNewDriveClick);

        // Configure combo box
        view.driveButtonCellProperty().set(new LoginDriveCell());
        view.driveCellFactoryProperty().set(listView -> new LoginDriveCell());
        view.drivesProperty().bind(viewModel.drivesProperty());
        view.driveProperty().addListener((observable, oldValue, newValue) -> {
            viewModel.selectedDriveProperty().set(newValue);
        });
        view.onShowingDrivesProperty().set(event -> {
            viewModel.loadDrives();
        });

        // Set hyperlink's actions
        view.onWebsiteLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.WEBSITE));
        view.onDocumentationLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.DOCUMENTATION));
        view.onRepositoryLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.REPOSITORY));

        // Bind properties
        view.usernameProperty().bindBidirectional(viewModel.usernameProperty());
        view.passwordProperty().bindBidirectional(viewModel.passwordProperty());
    }

    private void onNewDriveClick(ActionEvent actionEvent) {
        // TODO: Show FormatDriveDialog
    }

    private void onLoginButtonClick(ActionEvent actionEvent) {
        boolean loggedIn = viewModel.tryLogin();
        if(loggedIn) {
            ui.navigation().goToMain();
        } else {
            LoginFailedAlert alert = new LoginFailedAlert();
            alert.messageProperty().bind(viewModel.loginErrorMessageProperty());
            Stage stage = ui.navigation().getDialogStage(alert);
            alert.stageProperty().set(stage);
            if(alert.resultProperty().get() == DialogResult.SAVED) {
                // TODO: Save to database
                System.out.println("Saving platform to database");
            }
        }
    }

}
