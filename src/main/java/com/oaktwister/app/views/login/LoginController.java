package com.oaktwister.app.views.login;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.viewmodels.views.LoginViewModel;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.widgets.dialogs.DialogResult;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class LoginController extends Controller<LoginLayout> {

    private final UIContext ui;
    private final LoginViewModel viewModel;

    private final LoginLayout layout;

    public LoginController(UIContext ui) {
        this.ui = ui;
        viewModel = ui.viewModels().login();
        layout = new LoginLayout();
    }

    @Override
    protected LoginLayout initialize() {

        // Set button's actions
        layout.onLoginActionProperty().set(this::onLoginButtonClick);
        layout.onNewDriveLinkActionProperty().set(this::onNewDriveClick);

        // Configure combo box
        layout.driveButtonCellProperty().set(new LoginDriveCell());
        layout.driveCellFactoryProperty().set(listView -> new LoginDriveCell());
        layout.drivesProperty().bind(viewModel.drivesProperty());
        layout.driveProperty().addListener((observable, oldValue, newValue) -> {
            viewModel.selectedDriveProperty().set(newValue);
        });
        layout.onShowingDrivesProperty().set(event -> {
            viewModel.loadDrives();
        });

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
