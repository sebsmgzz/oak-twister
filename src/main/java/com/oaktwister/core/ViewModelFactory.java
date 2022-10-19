package com.oaktwister.core;

import com.oaktwister.services.AppConfig;
import com.oaktwister.services.Context;
import com.oaktwister.services.DriveFactory;
import com.oaktwister.services.json.*;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.services.util.LocalDateTimeUtil;
import com.oaktwister.viewmodels.collections.AccountsViewModel;
import com.oaktwister.viewmodels.collections.IdentitiesViewModel;
import com.oaktwister.viewmodels.collections.PlatformsViewModel;
import com.oaktwister.viewmodels.main.LandingViewModel;
import com.oaktwister.viewmodels.main.MainViewModel;
import com.oaktwister.viewmodels.models.AccountViewModel;

public class ViewModelFactory {

    private LandingViewModel landingViewModel;
    private MainViewModel mainViewModel;
    private IdentitiesViewModel identitiesViewModel;
    private PlatformsViewModel platformsViewModel;
    private AccountsViewModel accountsViewModel;

    public LandingViewModel getLandingViewModel() {
        if(landingViewModel == null) {
            Context context = Context.getInstance();
            AppConfig appConfig = AppConfig.getInstance();
            DriveFactory driveFactory = new DriveFactory(appConfig);
            landingViewModel = new LandingViewModel(context, driveFactory);
        }
        return landingViewModel;
    }

    public MainViewModel getMainViewModel() {
        if(mainViewModel == null) {
            Logger logger = new Logger(MainViewModel.class);
            mainViewModel = new MainViewModel(logger);
        }
        return mainViewModel;
    }

    public IdentitiesViewModel getIdentitiesViewModel() {
        if(identitiesViewModel == null) {
            Context context = Context.getInstance();

            LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
            Logger grantSerializerLogger = new Logger(GrantSerializer.class);
            GrantSerializer grantSerializer = new GrantSerializer(localDateTimeUtil, grantSerializerLogger);

            Logger grantMapSerializerLogger = new Logger(GrantMapSerializer.class);
            GrantMapSerializer grantMapSerializer = new GrantMapSerializer(grantSerializer, grantMapSerializerLogger);

            IdentitySerializer identitySerializer = new IdentitySerializer(grantMapSerializer, localDateTimeUtil);

            Logger identitiesRepoLogger = new Logger(IdentitiesRepo.class);
            IdentitiesRepo identitiesRepo = new IdentitiesRepo(context, identitySerializer, identitiesRepoLogger);

            Logger logger = new Logger(IdentitiesViewModel.class);
            identitiesViewModel = new IdentitiesViewModel(identitiesRepo, logger);
        }
        return identitiesViewModel;
    }

    public AccountsViewModel getAccountsViewModel() {
        if(accountsViewModel == null) {
            Context context = Context.getInstance();

            LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
            Logger claimSerializerLogger = new Logger(GrantSerializer.class);
            GrantSerializer grantSerializer = new GrantSerializer(localDateTimeUtil, claimSerializerLogger);
            Logger claimMapSerializerLogger = new Logger(ClaimMapSerializer.class);
            GrantMapSerializer grantMapSerializer = new GrantMapSerializer(grantSerializer, claimMapSerializerLogger);

            AccountSerializer accountSerializer = new AccountSerializer(grantMapSerializer, localDateTimeUtil);

            Logger accountsRepoLogger = new Logger(AccountsRepo.class);
            AccountsRepo accountsRepo = new AccountsRepo(context, accountSerializer, accountsRepoLogger);

            Logger viewModelLogger = new Logger(AccountsViewModel.class);
            accountsViewModel = new AccountsViewModel(this, accountsRepo, viewModelLogger);
        }
        return accountsViewModel;
    }

    public PlatformsViewModel getPlatformsViewModel() {
        if(platformsViewModel == null) {
            Context context = Context.getInstance();

            Logger imagesRepoLogger = new Logger(ImagesRepo.class);
            ImagesRepo imagesRepo = new ImagesRepo(context, imagesRepoLogger);

            ClaimSerializer claimSerializer = new ClaimSerializer();
            Logger claimMapSerializerLogger = new Logger(ClaimMapSerializer.class);
            ClaimMapSerializer claimMapSerializer = new ClaimMapSerializer(claimSerializer, claimMapSerializerLogger);

            LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
            PlatformSerializer platformSerializer = new PlatformSerializer(claimMapSerializer, localDateTimeUtil);

            Logger platformsRepoLogger = new Logger(PlatformsRepo.class);
            PlatformsRepo platformsRepo = new PlatformsRepo(context, platformSerializer, platformsRepoLogger);

            Logger viewModelLogger = new Logger(PlatformsViewModel.class);
            platformsViewModel = new PlatformsViewModel(imagesRepo, platformsRepo, viewModelLogger);
        }
        return platformsViewModel;
    }

    public AccountViewModel getAccountViewModel() {
        Context context = Context.getInstance();

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        Logger grantSerializerLogger = new Logger(GrantSerializer.class);
        GrantSerializer grantSerializer = new GrantSerializer(localDateTimeUtil, grantSerializerLogger);

        Logger grantMapSerializerLogger = new Logger(GrantMapSerializer.class);
        GrantMapSerializer grantMapSerializer = new GrantMapSerializer(grantSerializer, grantMapSerializerLogger);

        AccountSerializer accountSerializer = new AccountSerializer(grantMapSerializer, localDateTimeUtil);
        Logger accountsRepoLogger = new Logger(AccountsRepo.class);
        AccountsRepo accountsRepo = new AccountsRepo(context, accountSerializer, accountsRepoLogger);

        Logger imagesRepoLogger = new Logger(ImagesRepo.class);
        ImagesRepo imagesRepo = new ImagesRepo(context, imagesRepoLogger);

        ClaimSerializer claimSerializer = new ClaimSerializer();
        Logger claimMapSerializerLogger = new Logger(ClaimMapSerializer.class);
        ClaimMapSerializer claimMapSerializer = new ClaimMapSerializer(claimSerializer, claimMapSerializerLogger);

        PlatformSerializer platformSerializer = new PlatformSerializer(claimMapSerializer, localDateTimeUtil);
        Logger platformsRepoLogger = new Logger(PlatformsRepo.class);
        PlatformsRepo platformsRepo = new PlatformsRepo(context, platformSerializer, platformsRepoLogger);

        IdentitySerializer identitySerializer = new IdentitySerializer(grantMapSerializer, localDateTimeUtil);

        Logger identitiesRepoLogger = new Logger(IdentitiesRepo.class);
        IdentitiesRepo identitiesRepo = new IdentitiesRepo(context, identitySerializer, identitiesRepoLogger);

        return new AccountViewModel(accountsRepo, platformsRepo, identitiesRepo, imagesRepo);
    }

}
