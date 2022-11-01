package com.oaktwister.controllers.layouts;

import com.oaktwister.core.UIContext;
import com.oaktwister.views.controls.IdentityPane;
import com.oaktwister.views.controls.PagePane;
import com.oaktwister.views.layouts.MainLayout;
import com.oaktwister.views.layouts.MainPage;

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
        PagePane<IdentityPane> identitiesPanePage = ui.controllers().identities().getView();
        view.identitiesPageProperty().set(identitiesPanePage);
        view.onBackActionProperty().set(event -> ui.navigation().goToLogin());
        view.onSettingsActionProperty().set(event -> { /* TODO */ });
        view.pageProperty().set(MainPage.ACCOUNTS);
    }

    private void onPagePropertyChanged(ObservableValue<? extends MainPage> observable,
                                       MainPage oldValue, MainPage newValue) {
        switch (newValue) {
            case ACCOUNTS -> ui.controllers().accounts().reloadAccounts();
            case PLATFORMS -> ui.controllers().platforms().reloadPlatforms();
            case IDENTITIES -> ui.controllers().identities().reloadIdentities();
        }
    }

}
