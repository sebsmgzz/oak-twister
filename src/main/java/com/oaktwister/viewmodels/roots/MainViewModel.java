package com.oaktwister.viewmodels.roots;

import com.oaktwister.core.ViewModelFactory;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.views.windows.main.Page;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainViewModel {

    private final Logger logger;
    private final AccountsViewModel accountsViewModel;
    private final PlatformsViewModel platformsViewModel;
    private final IdentitiesViewModel identitiesViewModel;

    private final SimpleObjectProperty<Page> section;

    public MainViewModel(ViewModelFactory viewModelFactory, Logger logger) {
        this.logger = logger;
        this.accountsViewModel = viewModelFactory.getAccountsViewModel();
        this.platformsViewModel = viewModelFactory.getPlatformsViewModel();
        this.identitiesViewModel = viewModelFactory.getIdentitiesViewModel();
        section = new SimpleObjectProperty<>();
    }

    public AccountsViewModel accounts() {
        return accountsViewModel;
    }

    public PlatformsViewModel platforms() {
        return platformsViewModel;
    }

    public IdentitiesViewModel identities() {
        return identitiesViewModel;
    }

    public ObjectProperty<Page> sectionProperty() {
        return section;
    }

}
