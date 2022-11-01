package com.oaktwister.controllers.layouts;

import com.oaktwister.controllers.controls.AccountsController;
import com.oaktwister.controllers.controls.IdentitiesController;
import com.oaktwister.controllers.controls.PlatformsController;
import com.oaktwister.core.Navigation;
import com.oaktwister.core.ViewModelFactory;

import com.oaktwister.views.layouts.MainLayout;
import com.oaktwister.views.layouts.MainLayoutPage;
import javafx.beans.value.ObservableValue;

public class MainController {

    private final Navigation navigation;
    private final MainLayout view;

    private final AccountsController accountsController;
    private final PlatformsController platformsController;
    private final IdentitiesController identitiesController;

    public MainController(Navigation navigation, ViewModelFactory viewModelFactory) {
        this.navigation = navigation;
        view = new MainLayout();
        accountsController = new AccountsController(navigation, viewModelFactory);
        platformsController = new PlatformsController(navigation, viewModelFactory);
        identitiesController = new IdentitiesController(navigation, viewModelFactory);
    }

    public void initialize() {
        view.pageProperty().addListener(this::onPagePropertyChanged);
        view.accountPageProperty().set(accountsController.getView());
        view.platformsPageProperty().set(platformsController.getView());
        view.identitiesPageProperty().set(identitiesController.getView());
        view.onBackActionProperty().set(event -> navigation.goToLogin());
        view.onSettingsActionProperty().set(event -> { /* TODO */ });
        accountsController.initialize();
        platformsController.initialize();
        identitiesController.initialize();
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainLayoutPage> observable,
                                       MainLayoutPage oldValue, MainLayoutPage newValue) {
        switch (newValue) {
            case ACCOUNTS -> accountsController.onShowing();
            case PLATFORMS -> platformsController.onShowing();
            case IDENTITIES -> identitiesController.onShowing();
        }
    }

}
