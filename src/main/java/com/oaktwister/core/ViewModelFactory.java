package com.oaktwister.core;

import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.services.util.UUIDUtil;
import com.oaktwister.viewmodels.models.GrantMapViewModel;
import com.oaktwister.viewmodels.models.IdentityViewModel;
import com.oaktwister.viewmodels.models.PlatformViewModel;
import com.oaktwister.viewmodels.pages.AccountsViewModel;
import com.oaktwister.viewmodels.pages.IdentitiesViewModel;
import com.oaktwister.viewmodels.pages.PlatformsViewModel;
import com.oaktwister.viewmodels.roots.LandingViewModel;
import com.oaktwister.viewmodels.roots.MainViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;

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
            Logger logger = new Logger(IdentitiesViewModel.class);
            identitiesViewModel = new IdentitiesViewModel(serviceFactory.getIdentitiesRepo(), logger);
            serviceFactory.clearScope();
        }
        return identitiesViewModel;
    }

    public AccountsViewModel getAccountsViewModel() {
        if(accountsViewModel == null) {
            Logger logger = new Logger(AccountsViewModel.class);
            accountsViewModel = new AccountsViewModel(this,  serviceFactory.getAccountsRepo(), logger);
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
            platformsViewModel = new PlatformsViewModel(platformsRepo, imagesRepo, uuidUtil, logger);
            serviceFactory.clearScope();
        }
        return platformsViewModel;
    }

    public AccountViewModel getAccountViewModel() {
        AccountsRepo accountsRepo = serviceFactory.getAccountsRepo();
        PlatformsRepo platformsRepo = serviceFactory.getPlatformsRepo();
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        AccountViewModel viewModel = new AccountViewModel(
                this, accountsRepo, platformsRepo, identitiesRepo, uuidUtil);
        serviceFactory.clearScope();
        return viewModel;
    }

    public IdentityViewModel getIdentityViewModel() {
        IdentitiesRepo identitiesRepo = serviceFactory.getIdentitiesRepo();
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        IdentityViewModel viewModel = new IdentityViewModel(identitiesRepo, uuidUtil);
        serviceFactory.clearScope();
        return viewModel;
    }

    public PlatformViewModel getPlatformViewModel() {
        ImagesRepo imagesRepo = serviceFactory.getImagesRepo();
        UUIDUtil uuidUtil = serviceFactory.getUUIDUtil();
        PlatformViewModel viewModel = new PlatformViewModel(imagesRepo, uuidUtil);
        serviceFactory.clearScope();
        return viewModel;
    }

    public GrantMapViewModel getGrantMapViewModel() {
        GrantMapViewModel viewModel = new GrantMapViewModel();
        serviceFactory.clearScope();
        return viewModel;
    }

}
