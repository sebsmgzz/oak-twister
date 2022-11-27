package com.oaktwister.app.views.login;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.utils.listeners.ListItemAddedListener;
import com.oaktwister.app.utils.listeners.ListItemRemovedListener;
import com.oaktwister.app.viewmodels.models.DriveViewModel;
import com.oaktwister.app.viewmodels.views.LoginViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class LoginController extends Controller<LoginLayout> {

    // Context
    private final UIContext ui;
    private final LoginViewModel viewModel;

    // UI
    private final LoginLayout layout;

    // Listeners and mappings
    private final ListItemAddedListener<DriveViewModel> driveAddedListener;
    private final ListItemRemovedListener<DriveViewModel> driveRemovedListener;

    public LoginController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().login();
        layout = new LoginLayout();
        driveAddedListener = new ListItemAddedListener<>(this::onDriveAdded);
        driveRemovedListener = new ListItemRemovedListener<>(this::onDriveRemoved);
    }

    @Override
    protected LoginLayout initialize() {

        // Set button's actions
        layout.onLoginActionProperty().set(this::onLoginButtonClick);
        layout.onNewDriveLinkActionProperty().set(this::onNewDriveClick);

        // Configure combo box
        viewModel.drivesProperty().addListener(driveAddedListener);
        viewModel.drivesProperty().addListener(driveRemovedListener);
        viewModel.selectedDriveProperty().bind(layout.drives().selectedDriveProperty());
        layout.drives().setOnShowing(event -> viewModel.loadDrives());

        // Set hyperlink's actions
        layout.onWebsiteLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.WEBSITE));
        layout.onDocumentationLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.DOCUMENTATION));
        layout.onRepositoryLinkActionProperty().set(event -> viewModel.browse(StringResources.Url.REPOSITORY));

        // Bind properties
        layout.usernameProperty().bindBidirectional(viewModel.usernameProperty());
        layout.passwordProperty().bindBidirectional(viewModel.passwordProperty());

        return layout;
    }

    public void configStage(Stage stage) {
        stage.getIcons().add(new Image(ImageResources.Vikings.OAK));
        stage.setTitle(StringResources.App.TITLE);
    }

    private void onDriveAdded(DriveViewModel drive) {
        layout.drives().addDrive(drive);
    }

    private void onDriveRemoved(DriveViewModel drive) {
        layout.drives().removeDrive(drive);
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
            Stage stage = ui.stages().getDialogStage(alert);
            alert.stageProperty().set(stage);
            if(alert.resultProperty().get() == DialogResult.SAVED) {
                // TODO: Save to database
                System.out.println("Saving platform to database");
            }
        }
    }

}
