package com.oaktwister.core;

import com.oaktwister.viewmodels.landing.LandingViewModel;
import com.oaktwister.viewmodels.main.AccountsViewModel;
import com.oaktwister.viewmodels.main.IdentitiesViewModel;
import com.oaktwister.viewmodels.main.MainViewModel;
import com.oaktwister.viewmodels.main.PlatformsViewModel;

public class ViewModelFactory {

    private LandingViewModel landingViewModel;
    private MainViewModel mainViewModel;
    private IdentitiesViewModel identitiesViewModel;
    private PlatformsViewModel platformsViewModel;
    private AccountsViewModel accountsViewModel;

    public LandingViewModel getLandingViewModel() {
        if(landingViewModel == null) {
            landingViewModel = new LandingViewModel();
        }
        return landingViewModel;
    }

    public MainViewModel getMainViewModel() {
        if(mainViewModel == null) {
            mainViewModel = new MainViewModel();
        }
        return mainViewModel;
    }

    public IdentitiesViewModel getIdentitiesViewModel() {
        if(identitiesViewModel == null) {
            identitiesViewModel = new IdentitiesViewModel();
        }
        return identitiesViewModel;
    }

    public AccountsViewModel getAccountsViewModel() {
        if(accountsViewModel == null) {
            accountsViewModel = new AccountsViewModel();
        }
        return accountsViewModel;
    }

    public PlatformsViewModel getPlatformsViewModel() {
        if(platformsViewModel == null) {
            platformsViewModel = new PlatformsViewModel();
        }
        return platformsViewModel;
    }

}
