package com.oaktwister.app.controllers.layouts;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.views.accounts.AccountsPage;
import com.oaktwister.app.views.identities.IdentitiesPage;
import com.oaktwister.app.views.platforms.PlatformsPage;
import com.oaktwister.app.views.main.MainLayout;
import com.oaktwister.app.views.main.MainPage;

import javafx.beans.value.ObservableValue;

public class MainController {

    private final UIContext ui;

    private final MainLayout view;
    private final AccountsPage accountsPage;
    private final PlatformsPage platformsPage;
    private final IdentitiesPage identitiesPage;

    public MainController(UIContext ui) {
        this.ui = ui;
        view = new MainLayout();
        accountsPage = new AccountsPage(ui);
        platformsPage = new PlatformsPage(ui);
        identitiesPage = new IdentitiesPage(ui);
    }

    public MainLayout getView() {
        return view;
    }

    public void initialize() {
        view.pageProperty().addListener(this::onPagePropertyChanged);
        view.accountPageProperty().set(accountsPage);
        view.platformsPageProperty().set(platformsPage);
        view.identitiesPageProperty().set(identitiesPage);
        view.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        view.onSettingsActionProperty().set(event -> { /* TODO */ });
        view.pageProperty().set(MainPage.ACCOUNTS);
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        switch (newValue) {
            case ACCOUNTS -> {
                accountsPage.reloadAccounts();
                view.titleProperty().set(StringResources.ACCOUNTS);
            }
            case PLATFORMS -> {
                platformsPage.reloadPlatforms();
                view.titleProperty().set(StringResources.PLATFORMS);
            }
            case IDENTITIES -> {
                identitiesPage.reloadIdentities();
                view.titleProperty().set(StringResources.IDENTITIES);
            }
        }
    }

}
