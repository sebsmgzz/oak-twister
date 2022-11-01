package com.oaktwister.controllers.layouts;

import com.oaktwister.core.UIContext;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.viewmodels.roots.LoginViewModel;
import com.oaktwister.views.layouts.LoginLayout;
import com.oaktwister.views.layouts.LoginLayoutDriveCell;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

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
        view.onNewUserActionProperty().set(this::onNewUserButtonClick);
        view.onNewDriveActionProperty().set(this::onNewDriveButtonClick);
        view.onLoginActionProperty().set(this::onLoginButtonClick);

        // Configure combo box
        view.driveButtonCellProperty().set(new LoginLayoutDriveCell());
        view.driveCellFactoryProperty().set(listView -> new LoginLayoutDriveCell());
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

    private void onNewDriveButtonClick(ActionEvent actionEvent) {
        // TODO: Show FormatDriveDialog
    }

    private void onNewUserButtonClick(ActionEvent actionEvent) {
        // TODO: Show FormatUserDialog
    }

    private void onLoginButtonClick(ActionEvent actionEvent) {
        boolean loggedIn = viewModel.tryLogin();
        if(loggedIn) {
            ui.navigation().goToMain();
        } else {
            String errorMessage = viewModel.loginErrorMessageProperty().get();
            ui.navigation().showLoginFailedAlert(errorMessage);
        }
    }

}
