package com.oaktwister.app.core;

import com.oaktwister.domain.models.drives.Drive;
import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.drives.DriveLoader;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.AccountsRepo;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.infrastructure.repos.ImagesRepo;
import com.oaktwister.infrastructure.repos.PlatformsRepo;
import com.oaktwister.app.utils.Lazy;
import com.oaktwister.app.viewmodels.views.AccountsViewModel;
import com.oaktwister.app.viewmodels.views.IdentitiesViewModel;
import com.oaktwister.app.viewmodels.views.PlatformsViewModel;
import com.oaktwister.app.viewmodels.models.AccountViewModel;
import com.oaktwister.app.viewmodels.models.claims.ClaimMapViewModel;
import com.oaktwister.app.viewmodels.models.claims.ClaimViewModel;
import com.oaktwister.app.viewmodels.models.DriveViewModel;
import com.oaktwister.app.viewmodels.models.grants.GrantMapViewModel;
import com.oaktwister.app.viewmodels.models.IdentityViewModel;
import com.oaktwister.app.viewmodels.models.PlatformViewModel;
import com.oaktwister.app.viewmodels.views.LoginViewModel;

public class ViewModelFactory {

    private final ServiceFactory serviceFactory;

    private final Lazy<LoginViewModel> loginViewModel;
    private final Lazy<IdentitiesViewModel> identitiesViewModel;
    private final Lazy<PlatformsViewModel> platformsViewModel;
    private final Lazy<AccountsViewModel> accountsViewModel;

    public ViewModelFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        loginViewModel = new Lazy<>(this::getLoginViewModel);
        identitiesViewModel = new Lazy<>(this::getIdentitiesViewModel);
        platformsViewModel = new Lazy<>(this::getPlatformsViewModel);
        accountsViewModel = new Lazy<>(this::getAccountsViewModel);
    }

    private LoginViewModel getLoginViewModel() {
        SessionSettings session = serviceFactory.getSessionSettings();
        DriveLoader driveLoader = serviceFactory.getDriveLoader();
        serviceFactory.clearScope();
        return new LoginViewModel(this, session, driveLoader);
    }

    public LoginViewModel login() {
        return loginViewModel.value();
    }

    private IdentitiesViewModel getIdentitiesViewModel() {
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        Logger logger = new Logger(IdentitiesViewModel.class);
        serviceFactory.clearScope();
        return new IdentitiesViewModel(this, identitiesRepo, logger);
    }

    public IdentitiesViewModel identities() {
        return identitiesViewModel.value();
    }

    private AccountsViewModel getAccountsViewModel() {
        AccountsRepo accountsRepo = serviceFactory.getAccountsRepo();
        Logger logger = new Logger(AccountsViewModel.class);
        serviceFactory.clearScope();
        return new AccountsViewModel(this,  accountsRepo, logger);
    }

    public AccountsViewModel accounts() {
        return accountsViewModel.value();
    }

    private PlatformsViewModel getPlatformsViewModel() {
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        Logger logger = new Logger(PlatformsViewModel.class);
        serviceFactory.clearScope();
        return new PlatformsViewModel(this, platformsRepo, logger);
    }

    public PlatformsViewModel platforms() {
        return platformsViewModel.value();
    }

    public AccountViewModel account() {
        AccountsRepo accountsRepo = serviceFactory.getAccountsRepo();
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        Logger logger = new Logger(AccountViewModel.class);
        AccountViewModel viewModel = new AccountViewModel(
                this, accountsRepo, platformsRepo, identitiesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public IdentityViewModel identity() {
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        Logger logger = new Logger(IdentitiesViewModel.class);
        IdentityViewModel viewModel = new IdentityViewModel(
                this, identitiesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public PlatformViewModel platform() {
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        ImagesRepo imagesRepo = serviceFactory.getImagesRepo();
        Logger logger = new Logger(PlatformViewModel.class);
        PlatformViewModel viewModel = new PlatformViewModel(
                this, platformsRepo, imagesRepo, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public GrantMapViewModel grantMap() {
        GrantMapViewModel viewModel = new GrantMapViewModel(this);
        serviceFactory.clearScope();
        return viewModel;
    }

    public ClaimMapViewModel claimMap() {
        ClaimMapViewModel viewModel = new ClaimMapViewModel(this);
        serviceFactory.clearScope();
        return viewModel;
    }

    public DriveViewModel drive(Drive drive) {
        DriveViewModel driveViewModel = new DriveViewModel(drive, this);
        serviceFactory.clearScope();
        return driveViewModel;
    }

    public ClaimViewModel claim() {
        ClaimViewModel claimViewModel = new ClaimViewModel();
        serviceFactory.clearScope();
        return claimViewModel;
    }

}
