package com.oaktwister.app.views.main;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.accounts.AccountsController;
import com.oaktwister.app.views.identities.IdentitiesController;
import com.oaktwister.app.views.platforms.PlatformsController;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public final class MainController extends Controller<MainLayout> {

    private final UIContext ui;

    private final MainLayout node;
    private final AccountsController accounts;
    private final PlatformsController platforms;
    private final IdentitiesController identities;

    public MainController(UIContext ui) {
        this.ui = ui;
        node = new MainLayout();
        accounts = new AccountsController(ui);
        platforms = new PlatformsController(ui);
        identities = new IdentitiesController(ui);
    }

    @Override
    protected MainLayout instantiate() {
        node.pageProperty().addListener(this::onPagePropertyChanged);
        return node;
    }

    @Override
    protected void initialize(MainLayout node) {
        node.accountPageProperty().set(accounts.getNode());
        node.platformsPageProperty().set(platforms.getNode());
        node.identitiesPageProperty().set(identities.getNode());
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
                platforms.reloadPlatforms();
                titleProperty.set(StringResources.PLATFORMS);
            }
            case IDENTITIES -> {
                identities.reloadIdentities();
                titleProperty.set(StringResources.IDENTITIES);
            }
        }
    }

}
