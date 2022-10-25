package com.oaktwister.core;

import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.views.accounts.AccountPane;
import com.oaktwister.views.accounts.AccountsPane;
import com.oaktwister.views.identities.IdentitiesPane;
import com.oaktwister.views.identities.IdentityPane;
import com.oaktwister.views.laterals.ImageButtonBox;
import com.oaktwister.views.platforms.PlatformPane;
import com.oaktwister.views.platforms.PlatformsPane;

public class ControlFactory {

    private final ViewMediator viewMediator;
    private final ViewModelFactory viewModelFactory;

    public ControlFactory(ViewMediator viewMediator, ViewModelFactory viewModelFactory) {
        this.viewMediator = viewMediator;
        this.viewModelFactory = viewModelFactory;
    }

    public PlatformsPane getPlatformsPane(PlatformsViewModel viewModel) {
        PlatformsPane platformsPane = new PlatformsPane(viewMediator);
        platformsPane.setViewModel(viewModel);
        return platformsPane;
    }

    public PlatformsPane getPlatformsPane() {
        PlatformsViewModel viewModel = viewModelFactory.getPlatformsViewModel();
        return getPlatformsPane(viewModel);
    }

    public PlatformPane getPlatformPane(PlatformViewModel viewModel) {
        PlatformPane platformPane = new PlatformPane(viewMediator);
        platformPane.setViewModel(viewModel);
        return platformPane;
    }

    public PlatformPane getPlatformPane() {
        PlatformViewModel platformViewModel = viewModelFactory.getPlatformViewModel();
        return getPlatformPane(platformViewModel);
    }

    public AccountsPane getAccountsPane(AccountsViewModel viewModel) {
        AccountsPane accountsPane = new AccountsPane(viewMediator);
        accountsPane.setViewModel(viewModel);
        return accountsPane;
    }

    public AccountsPane getAccountsPane() {
        AccountsViewModel viewModel = viewModelFactory.getAccountsViewModel();
        return getAccountsPane(viewModel);
    }

    public AccountPane getAccountPane(AccountViewModel viewModel) {
        AccountPane accountPane = new AccountPane(viewMediator);
        accountPane.setViewModel(viewModel);
        return accountPane;
    }

    public AccountPane getAccountPane() {
        AccountViewModel viewModel = viewModelFactory.getAccountViewModel();
        return getAccountPane(viewModel);
    }

    public IdentitiesPane getIdentitiesPane(IdentitiesViewModel viewModel) {
        IdentitiesPane identitiesPane = new IdentitiesPane(viewMediator);
        identitiesPane.setViewModel(viewModel);
        return identitiesPane;
    }

    public IdentitiesPane getIdentitiesPane() {
        IdentitiesViewModel viewModel = viewModelFactory.getIdentitiesViewModel();
        return getIdentitiesPane(viewModel);
    }

    public IdentityPane getIdentityPane(IdentityViewModel viewModel) {
        IdentityPane identityPane = new IdentityPane(viewMediator);
        identityPane.setViewModel(viewModel);
        return identityPane;
    }

    public IdentityPane getIdentityPane() {
        IdentityViewModel viewModel = viewModelFactory.getIdentityViewModel();
        return getIdentityPane(viewModel);
    }

    public ImageButtonBox getImageButtonBox() {
        return new ImageButtonBox(viewMediator);
    }

}
