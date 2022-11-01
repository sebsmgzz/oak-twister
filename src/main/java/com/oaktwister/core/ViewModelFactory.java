package com.oaktwister.core;

import com.oaktwister.models.drives.Drive;
import com.oaktwister.services.configs.SessionSettings;
import com.oaktwister.services.drives.DriveLoader;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.parsers.GrantTypeParser;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;
import com.oaktwister.viewmodels.models.ClaimMapViewModel;
import com.oaktwister.viewmodels.models.ClaimViewModel;
import com.oaktwister.viewmodels.models.DriveViewModel;
import com.oaktwister.viewmodels.models.GrantMapViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.roots.LoginViewModel;

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
        GrantTypeParser grantTypeParser = serviceFactory.getGrantTypeParser();
        ClaimMapViewModel viewModel = new ClaimMapViewModel(this, grantTypeParser);
        serviceFactory.clearScope();
        return viewModel;
    }

    public DriveViewModel drive(Drive drive) {
        DriveViewModel driveViewModel = new DriveViewModel(drive, this);
        serviceFactory.clearScope();
        return driveViewModel;
    }

    public ClaimViewModel claim() {
        GrantTypeParser grantTypeParser = serviceFactory.getGrantTypeParser();
        ClaimViewModel claimViewModel = new ClaimViewModel(grantTypeParser);
        serviceFactory.clearScope();
        return claimViewModel;
    }

}
