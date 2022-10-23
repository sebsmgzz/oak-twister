package com.oaktwister.core;

import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.pages.AccountsViewModel;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.pages.PlatformsViewModel;
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
        return new PlatformsPane(viewMediator, viewModel);
    }

    public PlatformsPane getPlatformsPane() {
        PlatformsViewModel viewModel = viewModelFactory.getPlatformsViewModel();
        return getPlatformsPane(viewModel);
    }

    public PlatformPane getPlatformPane(PlatformViewModel viewModel) {
        return new PlatformPane(viewMediator, viewModel);
    }

    public PlatformPane getPlatformPane() {
        PlatformViewModel platformViewModel = viewModelFactory.getPlatformViewModel();
        return getPlatformPane(platformViewModel);
    }

    public AccountsPane getAccountsPane(AccountsViewModel viewModel) {
        return new AccountsPane(viewMediator, viewModel);
    }

    public AccountsPane getAccountsPane() {
        AccountsViewModel viewModel = viewModelFactory.getAccountsViewModel();
        return getAccountsPane(viewModel);
    }

    public AccountPane getAccountPane(AccountViewModel viewModel) {
        return new AccountPane(viewMediator, viewModel);
    }

    public AccountPane getAccountPane() {
        AccountViewModel viewModel = viewModelFactory.getAccountViewModel();
        return getAccountPane(viewModel);
    }

    public IdentitiesPane getIdentitiesPane(IdentitiesViewModel viewModel) {
        IdentitiesPane view = new IdentitiesPane(viewMediator);
        view.setViewModel(viewModel);
        return view;
    }

    public IdentitiesPane getIdentitiesPane() {
        IdentitiesViewModel viewModel = viewModelFactory.getIdentitiesViewModel();
        return  getIdentitiesPane(viewModel);
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
