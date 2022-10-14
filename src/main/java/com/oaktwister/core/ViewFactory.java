package com.oaktwister.core;

import com.oaktwister.views.layouts.AccountsPane;
import com.oaktwister.views.layouts.IdentitiesPane;
import com.oaktwister.views.layouts.PlatformsPane;
import java.io.IOException;

public class ViewFactory {

    private final ViewModelFactory viewModelFactory;

    public ViewFactory(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public AccountsPane getAccountsPane() throws IOException {
        return new AccountsPane();
    }

    public PlatformsPane getPlatformsPane() throws IOException {
        return new PlatformsPane(viewModelFactory.getPlatformsViewModel());
    }

    public IdentitiesPane getIdentitiesPane() throws IOException {
        return new IdentitiesPane();
    }

}
