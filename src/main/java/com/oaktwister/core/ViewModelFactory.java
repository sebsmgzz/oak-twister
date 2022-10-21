package com.oaktwister.core;

import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.util.extensions.LocalDateTimeUtil;
import com.oaktwister.util.extensions.UUIDUtil;
import com.oaktwister.viewmodels.models.*;
import com.oaktwister.viewmodels.pages.AccountsViewModel;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.pages.PlatformsViewModel;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;

public class ViewModelFactory {

    private final ServiceFactory serviceFactory;

    private LandingViewModel landingViewModel;
    private MainViewModel mainViewModel;
    private IdentitiesViewModel identitiesViewModel;
    private PlatformsViewModel platformsViewModel;
    private AccountsViewModel accountsViewModel;

    public ViewModelFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public LandingViewModel getLandingViewModel() {
        if(landingViewModel == null) {
            landingViewModel = new LandingViewModel(serviceFactory.getContext(), serviceFactory.getDriveFactory());
            serviceFactory.clearScope();
        }
        return landingViewModel;
    }

    public MainViewModel getMainViewModel() {
        if(mainViewModel == null) {
            Logger logger = new Logger(MainViewModel.class);
            mainViewModel = new MainViewModel(logger);
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
            ImagesRepo imagesRepo = serviceFactory.getImagesRepo();
            UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
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
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        LocalDateTimeUtil localDateTimeUtil = serviceFactory.getLocalDateTimeUtil();
        Logger logger = new Logger(AccountViewModel.class);
        AccountViewModel viewModel = new AccountViewModel(
                this, accountsRepo, platformsRepo, identitiesRepo, uuidUtil, localDateTimeUtil, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public IdentityViewModel getIdentityViewModel() {
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        LocalDateTimeUtil localDateTimeUtil = serviceFactory.getLocalDateTimeUtil();
        Logger logger = new Logger(IdentitiesViewModel.class);
        IdentityViewModel viewModel = new IdentityViewModel(
                this, identitiesRepo, uuidUtil, localDateTimeUtil, logger);
        serviceFactory.clearScope();
        return viewModel;
    }

    public PlatformViewModel getPlatformViewModel() {
        ImagesRepo imagesRepo = serviceFactory.getImagesRepo();
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        LocalDateTimeUtil localDateTimeUtil = serviceFactory.getLocalDateTimeUtil();
        PlatformViewModel viewModel = new PlatformViewModel(
                this, imagesRepo, uuidUtil, localDateTimeUtil);
        serviceFactory.clearScope();
        return viewModel;
    }

    public GrantMapViewModel getGrantMapViewModel() {
        GrantMapViewModel viewModel = new GrantMapViewModel();
        serviceFactory.clearScope();
        return viewModel;
    }

    public ClaimMapViewModel getClaimMapViewModel() {
        ClaimMapViewModel viewModel = new ClaimMapViewModel();
        serviceFactory.clearScope();
        return viewModel;
    }

}
