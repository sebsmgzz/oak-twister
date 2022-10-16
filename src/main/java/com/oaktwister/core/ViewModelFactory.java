package com.oaktwister.core;

import com.oaktwister.services.AppConfig;
import com.oaktwister.services.Context;
import com.oaktwister.services.DriveFactory;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.main.LandingViewModel;
import com.oaktwister.viewmodels.main.MainViewModel;

public class ViewModelFactory {

    private LandingViewModel landingViewModel;
    private MainViewModel mainViewModel;
    private IdentitiesViewModel identitiesViewModel;
    private PlatformsViewModel platformsViewModel;
    private AccountsViewModel accountsViewModel;

    public LandingViewModel getLandingViewModel() {
        if(landingViewModel == null) {
            landingViewModel = new LandingViewModel(
                    Context.getInstance(),
                    new DriveFactory(AppConfig.getInstance()));
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
            platformsViewModel = new PlatformsViewModel(new PlatformsRepo(Context.getInstance()));
        }
        return platformsViewModel;
    }

}
