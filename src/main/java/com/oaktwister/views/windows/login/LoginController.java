package com.oaktwister.views.windows.login;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.models.DriveViewModel;
import com.oaktwister.viewmodels.roots.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Windows.LOGIN)
public class LoginController implements Initializable {

    private final Navigation navigation;
    private final LoginViewModel viewModel;

    @FXML private Button newUserButton;
    @FXML private Button newDriveButton;
    @FXML private Hyperlink websiteHyperlink;
    @FXML private Hyperlink documentationHyperlink;
    @FXML private Hyperlink repositoryHyperlink;
    @FXML private ComboBox<DriveViewModel> driveComboBox;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button loginButton;

    public LoginController(Navigation navigation, LoginViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newUserButton.setOnAction(this::onNewUserButtonClick);
        newDriveButton.setOnAction(this::onNewDriveButtonClick);
        // TODO: Open browser when clicking hyperlink
        driveComboBox.setButtonCell(new DriveCell());
        driveComboBox.setCellFactory(listView -> new DriveCell());
        driveComboBox.itemsProperty().bind(viewModel.drivesProperty());
        driveComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            viewModel.selectedDriveProperty().set(newValue);
        });
        usernameTextField.textProperty().bindBidirectional(viewModel.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(viewModel.passwordProperty());
        viewModel.loadDrives();
        loginButton.setOnAction(this::onLoginButtonClick);
    }

    private void onNewDriveButtonClick(ActionEvent actionEvent) {
    }

    private void onNewUserButtonClick(ActionEvent actionEvent) {
    }

    private void onLoginButtonClick(ActionEvent actionEvent) {
        boolean loggedIn = viewModel.tryLogin();
        if(loggedIn) {
            navigation.goToMain();
        } else {
            // TODO: Show alert
        }
    }

}
