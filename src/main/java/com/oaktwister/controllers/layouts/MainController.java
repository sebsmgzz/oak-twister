package com.oaktwister.controllers.layouts;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.controllers.controls.AccountsController;
import com.oaktwister.controllers.controls.IdentitiesController;
import com.oaktwister.controllers.controls.PlatformsController;
import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.controls.PagePane;
import com.oaktwister.views.controls.AccountPane;
import com.oaktwister.views.controls.IdentityPane;
import com.oaktwister.views.controls.ImageButtonBox;
import com.oaktwister.views.controls.PlatformPane;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Windows.MAIN)
public class MainController implements Initializable {

    private final Navigation navigation;
    private final MainViewModel viewModel;

    private final AccountsController accountsController;
    private final PlatformsController platformsController;
    private final IdentitiesController identitiesController;

    @FXML private BorderPane borderPane;
    @FXML private ImageButtonBox accountsImageButtonBox;
    @FXML private ImageButtonBox platformsImageButtonBox;
    @FXML private ImageButtonBox identitiesImageButtonBox;
    @FXML private Button backButton;
    @FXML private Button settingsButton;

    public MainController(Navigation navigation, MainViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
        accountsController = new AccountsController(navigation, viewModel.accounts());
        platformsController = new PlatformsController(navigation, viewModel.platforms());
        identitiesController = new IdentitiesController(navigation, viewModel.identities());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bind the borderPane's center node with the centerNodeProperty
        viewModel.sectionProperty().addListener(this::onSectionChanged);

        // Set button's actions
        accountsImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Page.ACCOUNTS));
        platformsImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Page.PLATFORMS));
        identitiesImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Page.IDENTITIES));
        backButton.setOnAction(event -> navigation.goToLogin());
        settingsButton.setOnAction(event -> { /* TODO: Show Settings */ });

        // Initialize controllers
        accountsController.initialize(location, resources);
        platformsController.initialize(location, resources);
        identitiesController.initialize(location, resources);

        // Initialize page in Accounts
        viewModel.sectionProperty().set(Page.ACCOUNTS);

    }

    private void onSectionChanged(ObservableValue<? extends Page> observable, Page oldValue, Page newValue) {
        switch (newValue) {
            case ACCOUNTS -> {
                viewModel.accounts().clear();
                viewModel.accounts().load();
                PagePane<AccountPane> page = accountsController.getView();
                borderPane.setCenter(page);
            }
            case PLATFORMS -> {
                viewModel.platforms().clear();
                viewModel.platforms().load();
                PagePane<PlatformPane> page = platformsController.getView();
                borderPane.setCenter(page);
            }
            case IDENTITIES -> {
                viewModel.identities().clear();
                viewModel.identities().load();
                PagePane<IdentityPane> page = identitiesController.getView();
                borderPane.setCenter(page);
            }
        }
    }

}
