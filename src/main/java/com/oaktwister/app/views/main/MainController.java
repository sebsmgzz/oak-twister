package com.oaktwister.app.views.main;

import com.oaktwister.app.core.UIContext;
import com.oaktwister.app.services.resources.ImageResources;
import com.oaktwister.app.services.resources.StringResources;
import com.oaktwister.app.utils.Lazy;
import com.oaktwister.app.views.Controller;
import com.oaktwister.app.views.accounts.AccountsPage;
import com.oaktwister.app.views.identities.IdentitiesPage;
import com.oaktwister.app.views.platforms.PlatformsPage;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class MainController extends Controller<MainLayout> {

    private final UIContext ui;

    private final Lazy<AccountsPage> accountsPage;
    private final Lazy<PlatformsPage> platformsPage;
    private final Lazy<IdentitiesPage> identitiesPage;

    public MainController(UIContext ui) {
        this.ui = ui;
        accountsPage = new Lazy<>(() -> new AccountsPage(this.ui));
        platformsPage = new Lazy<>(() -> new PlatformsPage(this.ui));
        identitiesPage = new Lazy<>(() -> new IdentitiesPage(this.ui));
    }

    @Override
    protected MainLayout initializeNode() {
        MainLayout layout = new MainLayout();
        layout.pageProperty().addListener(this::onPagePropertyChanged);
        layout.accountPageProperty().set(accountsPage.value());
        layout.platformsPageProperty().set(platformsPage.value());
        layout.identitiesPageProperty().set(identitiesPage.value());
        layout.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        layout.onSettingsActionProperty().set(event -> { /* TODO */ });
        layout.pageProperty().set(MainPage.ACCOUNTS);
        return layout;
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        StringProperty titleProperty = node.value().titleProperty();
        switch (newValue) {
            case ACCOUNTS -> {
                accountsPage.value().reloadAccounts();
                titleProperty.set(StringResources.ACCOUNTS);
            }
            case PLATFORMS -> {
                platformsPage.value().reloadPlatforms();
                titleProperty.set(StringResources.PLATFORMS);
            }
            case IDENTITIES -> {
                identitiesPage.value().reloadIdentities();
                titleProperty.set(StringResources.IDENTITIES);
            }
        }
    }

}
