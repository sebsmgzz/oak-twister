package com.oaktwister.core;

import com.oaktwister.services.config.AppConfig;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.repos.DriveRepo;
import com.oaktwister.services.json.*;
import com.oaktwister.services.logging.Logger;
import com.oaktwister.services.repos.AccountsRepo;
import com.oaktwister.services.repos.IdentitiesRepo;
import com.oaktwister.services.repos.ImagesRepo;
import com.oaktwister.services.repos.PlatformsRepo;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.utils.extensions.UUIDUtil;

import java.util.HashMap;

public class ServiceFactory {

    private final HashMap<Class<?>, Object> singletons = new HashMap<>();
    private final HashMap<Class<?>, Object> scoped = new HashMap<>();

    public Context getContext() {
        if(singletons.containsKey(Context.class)) {
            return (Context) singletons.get(Context.class);
        } else {
            Context service = Context.getInstance();
            singletons.put(Context.class, service);
            return service;
        }
    }

    public AppConfig getAppConfig() {
        if(singletons.containsKey(AppConfig.class)) {
            return (AppConfig) singletons.get(AppConfig.class);
        } else {
            AppConfig service = AppConfig.getInstance();
            singletons.put(AppConfig.class, service);
            return service;
        }
    }

    public DriveRepo getDriveFactory() {
        if(scoped.containsKey(DriveRepo.class)) {
            return (DriveRepo) scoped.get(DriveRepo.class);
        } else {
            DriveRepo service = new DriveRepo(getAppConfig());
            scoped.put(DriveRepo.class, service);
            return service;
        }
    }

    public GrantSerializer getGrantSerializer() {
        if(scoped.containsKey(GrantSerializer.class)) {
            return (GrantSerializer) scoped.get(GrantSerializer.class);
        } else {
            Logger logger = new Logger(GrantSerializer.class);
            GrantSerializer service = new GrantSerializer(logger);
            scoped.put(GrantSerializer.class, service);
            return service;
        }
    }

    public GrantMapSerializer getGrantMapSerializer() {
        if(scoped.containsKey(GrantMapSerializer.class)) {
            return (GrantMapSerializer) scoped.get(GrantMapSerializer.class);
        } else {
            GrantSerializer grantSerializer = getGrantSerializer();
            Logger logger = new Logger(GrantMapSerializer.class);
            GrantMapSerializer service = new GrantMapSerializer(grantSerializer, logger);
            scoped.put(GrantMapSerializer.class, service);
            return service;
        }
    }

    public IdentitySerializer getIdentitySerializer() {
        if(scoped.containsKey(IdentitySerializer.class)) {
            return (IdentitySerializer) scoped.get(IdentitySerializer.class);
        } else {
            GrantMapSerializer grantMapSerializer = getGrantMapSerializer();
            IdentitySerializer service = new IdentitySerializer(grantMapSerializer);
            scoped.put(IdentitySerializer.class, service);
            return service;
        }
    }

    public AccountSerializer getAccountSerializer() {
        if(scoped.containsKey(AccountSerializer.class)) {
            return (AccountSerializer) scoped.get(AccountSerializer.class);
        } else {
            GrantMapSerializer grantMapSerializer = getGrantMapSerializer();
            AccountSerializer service = new AccountSerializer(grantMapSerializer);
            scoped.put(AccountSerializer.class, service);
            return service;
        }
    }

    public ImagesRepo getImagesRepo() {
        if(scoped.containsKey(ImagesRepo.class)) {
            return (ImagesRepo) scoped.get(ImagesRepo.class);
        } else {
            Context context = getContext();
            Logger logger = new Logger(ImagesRepo.class);
            ImagesRepo service = new ImagesRepo(context, logger);
            scoped.put(ImagesRepo.class, service);
            return service;
        }
    }

    public IdentitiesRepo getIdentitiesRepo() {
        if(scoped.containsKey(IdentitiesRepo.class)) {
            return (IdentitiesRepo) scoped.get(IdentitiesRepo.class);
        } else {
            Context context = Context.getInstance();
            AccountsRepo accountsRepo = getAccountsRepo();
            IdentitySerializer identitySerializer = getIdentitySerializer();
            Logger logger = new Logger(IdentitiesRepo.class);
            IdentitiesRepo service = new IdentitiesRepo(context, accountsRepo, identitySerializer, logger);
            scoped.put(IdentitiesRepo.class, service);
            return service;
        }
    }

    public PlatformSerializer getPlatformSerializer() {
        if(scoped.containsKey(PlatformSerializer.class)) {
            return (PlatformSerializer) scoped.get(PlatformSerializer.class);
        } else {
            ClaimMapSerializer claimMapSerializer = getClaimMapSerializer();
            Logger logger = new Logger(PlatformSerializer.class);
            PlatformSerializer service = new PlatformSerializer(claimMapSerializer, logger);
            scoped.put(PlatformSerializer.class, service);
            return service;
        }
    }

    public ClaimSerializer getClaimSerializer() {
        if(scoped.containsKey(ClaimSerializer.class)) {
            return (ClaimSerializer) scoped.get(ClaimSerializer.class);
        } else {
            Logger logger = new Logger(ClaimSerializer.class);
            ClaimSerializer service = new ClaimSerializer(logger);
            scoped.put(ClaimSerializer.class, service);
            return service;
        }
    }

    public ClaimMapSerializer getClaimMapSerializer() {
        if(scoped.containsKey(ClaimMapSerializer.class)) {
            return (ClaimMapSerializer) scoped.get(ClaimMapSerializer.class);
        } else {
            ClaimSerializer claimSerializer = getClaimSerializer();
            Logger logger = new Logger(ClaimMapSerializer.class);
            ClaimMapSerializer service = new ClaimMapSerializer(claimSerializer, logger);
            scoped.put(ClaimMapSerializer.class, service);
            return service;
        }
    }

    public AccountsRepo getAccountsRepo() {
        if(scoped.containsKey(AccountsRepo.class)) {
            return (AccountsRepo) scoped.get(AccountsRepo.class);
        } else {
            Context context = Context.getInstance();
            AccountSerializer accountSerializer = getAccountSerializer();
            Logger logger = new Logger(AccountsRepo.class);
            AccountsRepo service = new AccountsRepo(context, accountSerializer, logger);
            scoped.put(AccountsRepo.class, service);
            return service;
        }
    }

    public PlatformsRepo getPlatformsRepo() {
        if(scoped.containsKey(PlatformsRepo.class)) {
            return (PlatformsRepo) scoped.get(PlatformsRepo.class);
        } else {
            Context context = Context.getInstance();
            PlatformSerializer platformSerializer = getPlatformSerializer();
            AccountsRepo accountsRepo = getAccountsRepo();
            Logger logger = new Logger(PlatformsRepo.class);
            PlatformsRepo service = new PlatformsRepo(context, platformSerializer, accountsRepo, logger);
            scoped.put(PlatformsRepo.class, service);
            return service;
        }
    }

    public void clearScope() {
        scoped.clear();
    }

}
