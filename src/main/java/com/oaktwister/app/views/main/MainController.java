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

    private final AccountsController accounts;
    private final PlatformsController platforms;
    private final IdentitiesController identities;

    private final MainLayout layout;

    public MainController(UIContext ui) {
        this.ui = ui;
        layout = new MainLayout();
        accounts = new AccountsController(ui);
        platforms = new PlatformsController(ui);
        identities = new IdentitiesController(ui);
    }

    @Override
    protected MainLayout initialize() {
        layout.pageProperty().addListener(this::onPagePropertyChanged);
        layout.accountPageProperty().set(accounts.getRoot());
        layout.platformsPageProperty().set(platforms.getRoot());
        layout.identitiesPageProperty().set(identities.getRoot());
        layout.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        layout.onSettingsActionProperty().set(event -> { /* TODO */ });
        layout.pageProperty().set(MainPage.ACCOUNTS);
        return layout;
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        StringProperty titleProperty = layout.titleProperty();
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
