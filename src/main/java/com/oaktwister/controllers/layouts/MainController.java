package com.oaktwister.controllers.layouts;

import com.oaktwister.core.UIContext;

import com.oaktwister.views.layouts.MainLayout;
import com.oaktwister.views.layouts.MainLayoutPage;
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
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainLayoutPage> observable,
                                       MainLayoutPage oldValue, MainLayoutPage newValue) {
        switch (newValue) {
            case ACCOUNTS -> ui.controllers().accounts().reloadAccounts();
            case PLATFORMS -> ui.controllers().platforms().reloadPlatforms();
            case IDENTITIES -> ui.controllers().identities().reloadIdentities();
        }
    }

}
