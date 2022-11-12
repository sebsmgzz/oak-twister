package com.oaktwister.app.views.main;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.accounts.AccountsController;
import com.oaktwister.app.views.identities.IdentitiesPage;
import com.oaktwister.app.views.platforms.PlatformsPage;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public final class MainController extends Controller<MainLayout> {

    private final UIContext ui;

    private final MainLayout node;
    private final AccountsController accounts;
    private final PlatformsPage platformsPage;
    private final IdentitiesPage identitiesPage;

    public MainController(UIContext ui) {
        this.ui = ui;
        node = new MainLayout();
        accounts = new AccountsController(this.ui);
        platformsPage = new PlatformsPage(this.ui);
        identitiesPage = new IdentitiesPage(this.ui);
    }

    @Override
    protected MainLayout instantiate() {
        node.pageProperty().addListener(this::onPagePropertyChanged);
        return node;
    }

    @Override
    protected void initialize(MainLayout node) {
        node.accountPageProperty().set(accounts.getNode());
        node.platformsPageProperty().set(platformsPage);
        node.identitiesPageProperty().set(identitiesPage);
        node.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        node.onSettingsActionProperty().set(event -> { /* TODO */ });
        node.pageProperty().set(MainPage.ACCOUNTS);
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        StringProperty titleProperty = getNode().titleProperty();
        switch (newValue) {
            case ACCOUNTS -> {
                accounts.reloadAccounts();
                titleProperty.set(StringResources.ACCOUNTS);
            }
            case PLATFORMS -> {
                platformsPage.reloadPlatforms();
                titleProperty.set(StringResources.PLATFORMS);
            }
            case IDENTITIES -> {
                identitiesPage.reloadIdentities();
                titleProperty.set(StringResources.IDENTITIES);
            }
        }
    }

}
