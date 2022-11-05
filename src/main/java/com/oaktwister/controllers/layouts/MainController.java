package com.oaktwister.controllers.layouts;

import com.oaktwister.core.UIContext;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.views.accounts.AccountsPage;
import com.oaktwister.views.identities.IdentityPane;
import com.oaktwister.views.platforms.PlatformsPage;
import com.oaktwister.views.widgets.PagePane;
import com.oaktwister.views.main.MainLayout;
import com.oaktwister.views.main.MainPage;

import javafx.beans.value.ObservableValue;

public class MainController {

    private final UIContext ui;

    private final MainLayout view;
    private final AccountsPage accountsPage;
    private final PlatformsPage platformsPage;

    public MainController(UIContext ui) {
        this.ui = ui;
        view = new MainLayout();
        accountsPage = new AccountsPage(ui);
        platformsPage = new PlatformsPage(ui);
    }

    public MainLayout getView() {
        return view;
    }

    public void initialize() {
        view.pageProperty().addListener(this::onPagePropertyChanged);
        view.accountPageProperty().set(accountsPage);
        view.platformsPageProperty().set(platformsPage);
        view.identitiesPageProperty().set(ui.controllers().identities().getView());
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
                ui.controllers().identities().reloadIdentities();
                view.titleProperty().set(StringResources.IDENTITIES);
            }
        }
    }

}
