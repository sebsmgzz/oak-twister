package com.oaktwister.controllers.layouts;

import com.oaktwister.core.UIContext;
import com.oaktwister.services.resources.StringResources;
import com.oaktwister.views.identities.IdentityPane;
import com.oaktwister.views.widgets.PagePane;
import com.oaktwister.views.main.MainLayout;
import com.oaktwister.views.main.MainPage;

import javafx.beans.value.ObservableValue;

public class MainController {

    private final UIContext ui;

    private final MainLayout view;

    public MainController(UIContext ui) {
        this.ui = ui;
        view = new MainLayout();
    }

    public MainLayout getView() {
        return view;
    }

    public void initialize() {
        view.pageProperty().addListener(this::onPagePropertyChanged);
        view.accountPageProperty().set(ui.controllers().accounts().getView());
        view.platformsPageProperty().set(ui.controllers().platforms().getView());
        view.identitiesPageProperty().set(ui.controllers().identities().getView());
        view.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        view.onSettingsActionProperty().set(event -> { /* TODO */ });
        view.pageProperty().set(MainPage.ACCOUNTS);
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        switch (newValue) {
            case ACCOUNTS -> {
                ui.controllers().accounts().reloadAccounts();
                view.titleProperty().set(StringResources.ACCOUNTS);
            }
            case PLATFORMS -> {
                ui.controllers().platforms().reloadPlatforms();
                view.titleProperty().set(StringResources.PLATFORMS);
            }
            case IDENTITIES -> {
                ui.controllers().identities().reloadIdentities();
                view.titleProperty().set(StringResources.IDENTITIES);
            }
        }
    }

}
