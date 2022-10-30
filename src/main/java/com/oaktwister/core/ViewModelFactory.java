package com.oaktwister.core;

import com.oaktwister.models.drives.Drive;
import com.oaktwister.services.configs.SessionSettings;
import com.oaktwister.services.drives.DriveLoader;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.parsers.GrantTypeParser;
import com.oaktwister.services.repos.*;
import com.oaktwister.viewmodels.models.*;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.roots.LoginViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;

public class ViewModelFactory {

    private final ServiceFactory serviceFactory;

    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;
    private IdentitiesViewModel identitiesViewModel;
    private PlatformsViewModel platformsViewModel;
    private AccountsViewModel accountsViewModel;

    public ViewModelFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            SessionSettings session = serviceFactory.getSessionSettings();
            DriveLoader driveLoader = serviceFactory.getDriveLoader();
            loginViewModel = new LoginViewModel(this, session, driveLoader);
            serviceFactory.clearScope();
        }
        return loginViewModel;
    }

    public MainViewModel getMainViewModel() {
        if(mainViewModel == null) {
            Logger logger = new Logger(MainViewModel.class);
            mainViewModel = new MainViewModel(this, logger);
            serviceFactory.clearScope();
        }
        return mainViewModel;
    }

    public IdentitiesViewModel getIdentitiesViewModel() {
        if(identitiesViewModel == null) {
            IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
            Logger logger = new Logger(IdentitiesViewModel.class);
            identitiesViewModel = new IdentitiesViewModel(this, identitiesRepo, logger);
            serviceFactory.clearScope();
        }
        return identitiesViewModel;
    }

    public AccountsViewModel getAccountsViewModel() {
        if(accountsViewModel == null) {
            AccountsRepo accountsRepo = serviceFactory.getAccountsRepo();
            Logger logger = new Logger(AccountsViewModel.class);
            accountsViewModel = new AccountsViewModel(this,  accountsRepo, logger);
            serviceFactory.clearScope();
        }
        return accountsViewModel;
    }

    public PlatformsViewModel getPlatformsViewModel() {
        if(platformsViewModel == null) {
            PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
            Logger logger = new Logger(PlatformsViewModel.class);
            platformsViewModel = new PlatformsViewModel(this, platformsRepo, logger);
            serviceFactory.clearScope();
        }
        return platformsViewModel;
    }

    public AccountViewModel getAccountViewModel() {
        AccountsRepo accountsRepo = serviceFactory.getAccountsRepo();
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        Logger logger = new Logger(AccountViewModel.class);
        AccountViewModel viewModel = new AccountViewModel(
                this, accountsRepo, platformsRepo, identitiesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public IdentityViewModel getIdentityViewModel() {
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        Logger logger = new Logger(IdentitiesViewModel.class);
        IdentityViewModel viewModel = new IdentityViewModel(
                this, identitiesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public PlatformViewModel getPlatformViewModel() {
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        ImagesRepo imagesRepo = serviceFactory.getImagesRepo();
        Logger logger = new Logger(PlatformViewModel.class);
        PlatformViewModel viewModel = new PlatformViewModel(
                this, platformsRepo, imagesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public GrantMapViewModel getGrantMapViewModel() {
        GrantMapViewModel viewModel = new GrantMapViewModel(this);
        serviceFactory.clearScope();
        return viewModel;
    }

    public ClaimMapViewModel getClaimMapViewModel() {
        GrantTypeParser grantTypeParser = serviceFactory.getGrantTypeParser();
        ClaimMapViewModel viewModel = new ClaimMapViewModel(this, grantTypeParser);
        serviceFactory.clearScope();
        return viewModel;
    }

    public DriveViewModel getDriveViewModel(Drive drive) {
        DriveViewModel driveViewModel = new DriveViewModel(drive, this);
        serviceFactory.clearScope();
        return driveViewModel;
    }

    public ClaimViewModel getClaimViewModel() {
        GrantTypeParser grantTypeParser = serviceFactory.getGrantTypeParser();
        ClaimViewModel claimViewModel = new ClaimViewModel(grantTypeParser);
        serviceFactory.clearScope();
        return claimViewModel;
    }

}
