package com.oaktwister.views.windows.main;

import com.oaktwister.annotations.ViewDescriptor;
import com.oaktwister.core.Navigation;
import com.oaktwister.services.resources.ViewResources;
import com.oaktwister.utils.listeners.ListItemAddedListener;
import com.oaktwister.utils.listeners.ListItemRemovedListener;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.views.controls.pages.PagePane;
import com.oaktwister.views.controls.accounts.AccountPane;
import com.oaktwister.views.controls.identities.IdentityPane;
import com.oaktwister.views.controls.buttons.ImageButtonBox;
import com.oaktwister.views.controls.platforms.PlatformPane;

import com.oaktwister.views.dialogs.platforms.EditPlatformDialogResult;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@ViewDescriptor(location = ViewResources.Windows.MAIN)
public class MainViewController implements Initializable {

    private final Navigation navigation;
    private final MainViewModel viewModel;

    private final HashMap<AccountViewModel, AccountPane> accountsMap;
    private final HashMap<PlatformViewModel, PlatformPane> platformsMap;
    private final HashMap<IdentityViewModel, IdentityPane> identitiesMap;

    private final ListItemAddedListener<AccountViewModel> accountViewModelAddedListener;
    private final ListItemRemovedListener<AccountViewModel> accountViewModelRemovedListener;
    private final ListItemAddedListener<PlatformViewModel> platformViewModelAddedListener;
    private final ListItemRemovedListener<PlatformViewModel> platformViewModelRemovedListener;
    private final ListItemAddedListener<IdentityViewModel> identityViewModelAddedListener;
    private final ListItemRemovedListener<IdentityViewModel> identityViewModelRemovedListener;

    private final PagePane<AccountPane> accountsPane;
    private final PagePane<PlatformPane> platformsPane;
    private final PagePane<IdentityPane> identitiesPane;

    @FXML private BorderPane borderPane;
    @FXML private ImageButtonBox accountsImageButtonBox;
    @FXML private ImageButtonBox platformsImageButtonBox;
    @FXML private ImageButtonBox identitiesImageButtonBox;
    @FXML private Button backButton;
    @FXML private Button settingsButton;

    public MainViewController(Navigation navigation, MainViewModel viewModel) {
        this.navigation = navigation;
        this.viewModel = viewModel;
        accountsMap = new HashMap<>();
        platformsMap = new HashMap<>();
        identitiesMap = new HashMap<>();
        accountViewModelAddedListener = new ListItemAddedListener<>(this::onAccountViewModelAdded);
        accountViewModelRemovedListener = new ListItemRemovedListener<>(this::onAccountViewModelRemoved);
        platformViewModelAddedListener = new ListItemAddedListener<>(this::onPlatformViewModelAdded);
        platformViewModelRemovedListener = new ListItemRemovedListener<>(this::onPlatformViewModelRemoved);
        identityViewModelAddedListener = new ListItemAddedListener<>(this::onIdentityViewModelAdded);
        identityViewModelRemovedListener = new ListItemRemovedListener<>(this::onIdentityViewModelRemoved);
        accountsPane = new PagePane<>();
        platformsPane = new PagePane<>();
        identitiesPane = new PagePane<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Bind the borderPane's center node with the centerNodeProperty
        viewModel.sectionProperty().addListener(this::onSectionChanged);

        // Add view model's added/removed listeners
        viewModel.accounts().accountsProperty().addListener(accountViewModelAddedListener);
        viewModel.accounts().accountsProperty().addListener(accountViewModelRemovedListener);
        viewModel.platforms().platformsProperty().addListener(platformViewModelAddedListener);
        viewModel.platforms().platformsProperty().addListener(platformViewModelRemovedListener);
        viewModel.identities().identitiesProperty().addListener(identityViewModelAddedListener);
        viewModel.identities().identitiesProperty().addListener(identityViewModelRemovedListener);

        // Set button's actions
        accountsImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Section.ACCOUNTS));
        platformsImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Section.PLATFORMS));
        identitiesImageButtonBox.setOnAction(event -> viewModel.sectionProperty().set(Section.IDENTITIES));
        backButton.setOnAction(event -> navigation.goToLandingView());
        settingsButton.setOnAction(event -> { /* TODO */ });
        accountsPane.onAddActionProperty().set(this::onAddAccountPane);
        platformsPane.onAddActionProperty().set(this::onAddPlatformPane);
        identitiesPane.onAddActionProperty().set(this::onAddIdentityPane);

        // Initialize viewModel in AccountsSection
        viewModel.sectionProperty().set(Section.ACCOUNTS);

    }

    private void onSectionChanged(ObservableValue<? extends Section> observable, Section oldValue, Section newValue) {
        switch (newValue) {
            case ACCOUNTS -> {
                viewModel.accounts().clear();
                viewModel.accounts().load();
                borderPane.setCenter(accountsPane);
            }
            case PLATFORMS -> {
                viewModel.platforms().clear();
                viewModel.platforms().load();
                borderPane.setCenter(platformsPane);
            }
            case IDENTITIES -> {
                viewModel.identities().clear();
                viewModel.identities().load();
                borderPane.setCenter(identitiesPane);
            }
        }
    }

    private void onAccountViewModelAdded(AccountViewModel accountViewModel) {
        AccountPane accountPane = new AccountPane();
        accountPane.onMainActionProperty().set(this::onAccountPaneClick);
        accountPane.onDeleteActionProperty().set(this::onAccountPaneDeleteClick);
        accountPane.identifierProperty().bind(accountViewModel.idProperty());
        accountPane.createdAtProperty().bind(accountViewModel.createdAtProperty());
        accountPane.imageProperty().bind(accountViewModel.platform().imageProperty());
        accountPane.platformNameProperty().bind(accountViewModel.platform().nameProperty());
        accountPane.grantsCountProperty().bind(accountViewModel.grantMap().grantCountProperty());
        accountsPane.panesProperty().add(accountPane);
        accountsMap.put(accountViewModel, accountPane);
    }
    private void onAccountViewModelRemoved(AccountViewModel accountViewModel) {
        AccountPane accountPane = accountsMap.remove(accountViewModel);
        accountsPane.panesProperty().remove(accountPane);
    }

    private void onPlatformViewModelAdded(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = new PlatformPane();
        platformPane.onMainActionProperty().set(this::onPlatformPaneClick);
        platformPane.onDeleteActionProperty().set(this::onPlatformPaneDeleteClick);
        platformPane.identifierProperty().bind(platformViewModel.idProperty());
        platformPane.imageProperty().bind(platformViewModel.imageProperty());
        platformPane.nameProperty().bind(platformViewModel.nameProperty());
        platformPane.createdAtProperty().bind(platformViewModel.createdAtProperty());
        platformsPane.panesProperty().add(platformPane);
        platformsMap.put(platformViewModel, platformPane);
    }

    private void onPlatformViewModelRemoved(PlatformViewModel platformViewModel) {
        PlatformPane platformPane = platformsMap.remove(platformViewModel);
        platformsPane.panesProperty().remove(platformPane);
    }

    private void onIdentityViewModelAdded(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = new IdentityPane();
        identityPane.onMainActionProperty().set(this::onIdentityPaneClick);
        identityPane.onDeleteActionProperty().set(this::onIdentityPaneDeleteClick);
        identityPane.identifierProperty().bind(identityViewModel.idProperty());
        identityPane.grantsCountProperty().bind(identityViewModel.grantMap().grantCountProperty());
        identityPane.createdAtProperty().bind(identityViewModel.createdAtProperty());
        identitiesPane.panesProperty().add(identityPane);
        identitiesMap.put(identityViewModel, identityPane);
    }

    private void onIdentityViewModelRemoved(IdentityViewModel identityViewModel) {
        IdentityPane identityPane = identitiesMap.remove(identityViewModel);
        identitiesPane.panesProperty().remove(identityPane);
    }

    private void onAccountPaneClick(ActionEvent actionEvent) {
    }

    private void onAccountPaneDeleteClick(ActionEvent actionEvent) {
    }

    private void onPlatformPaneClick(ActionEvent actionEvent) {
    }

    private void onPlatformPaneDeleteClick(ActionEvent actionEvent) {

    }

    private void onIdentityPaneClick(ActionEvent actionEvent) {

    }

    private void onIdentityPaneDeleteClick(ActionEvent actionEvent) {

    }

    private void onAddAccountPane(ActionEvent actionEvent) {

    }

    private void onAddPlatformPane(ActionEvent actionEvent) {
        PlatformViewModel platformViewModel = navigation.viewModelFactory.getPlatformViewModel();
        EditPlatformDialogResult result = navigation.showEditPlatformDialog(platformViewModel);
        if(result == EditPlatformDialogResult.SAVED) {
            // TODO: Save to database
            System.out.println("Saving platform to database");
        }
    }

    private void onAddIdentityPane(ActionEvent actionEvent) {
    }

}
