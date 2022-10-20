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
import com.oaktwister.services.util.LocalDateTimeUtil;
import com.oaktwister.services.util.UUIDUtil;

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

    public LocalDateTimeUtil getLocalDateTimeUtil() {
        if(scoped.containsKey(LocalDateTimeUtil.class)) {
            return (LocalDateTimeUtil) scoped.get(LocalDateTimeUtil.class);
        } else {
            LocalDateTimeUtil service = new LocalDateTimeUtil();
            scoped.put(LocalDateTimeUtil.class, service);
            return service;
        }
    }

    public UUIDUtil getUUIDUtil() {
        if(scoped.containsKey(UUIDUtil.class)) {
            return (UUIDUtil) scoped.get(UUIDUtil.class);
        } else {
            UUIDUtil service = new UUIDUtil();
            scoped.put(UUIDUtil.class, service);
            return service;
        }
    }

    public GrantSerializer getGrantSerializer() {
        if(scoped.containsKey(GrantSerializer.class)) {
            return (GrantSerializer) scoped.get(GrantSerializer.class);
        } else {
            Logger logger = new Logger(GrantSerializer.class);
            GrantSerializer service = new GrantSerializer(getLocalDateTimeUtil(), logger);
            scoped.put(GrantSerializer.class, service);
            return service;
        }
    }

    public GrantMapSerializer getGrantMapSerializer() {
        if(scoped.containsKey(GrantMapSerializer.class)) {
            return (GrantMapSerializer) scoped.get(GrantMapSerializer.class);
        } else {
            Logger logger = new Logger(GrantMapSerializer.class);
            GrantMapSerializer service = new GrantMapSerializer(getGrantSerializer(), logger);
            scoped.put(GrantMapSerializer.class, service);
            return service;
        }
    }

    public IdentitySerializer getIdentitySerializer() {
        if(scoped.containsKey(IdentitySerializer.class)) {
            return (IdentitySerializer) scoped.get(IdentitySerializer.class);
        } else {
            IdentitySerializer service = new IdentitySerializer(getGrantMapSerializer(), getLocalDateTimeUtil());
            scoped.put(IdentitySerializer.class, service);
            return service;
        }
    }

    public AccountSerializer getAccountSerializer() {
        if(scoped.containsKey(AccountSerializer.class)) {
            return (AccountSerializer) scoped.get(AccountSerializer.class);
        } else {
            AccountSerializer service = new AccountSerializer(getGrantMapSerializer(), getLocalDateTimeUtil());
            scoped.put(AccountSerializer.class, service);
            return service;
        }
    }

    public ImagesRepo getImagesRepo() {
        if(scoped.containsKey(ImagesRepo.class)) {
            return (ImagesRepo) scoped.get(ImagesRepo.class);
        } else {
            Logger logger = new Logger(ImagesRepo.class);
            ImagesRepo service = new ImagesRepo(getContext(), logger);
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
            LocalDateTimeUtil localDateTimeUtil = getLocalDateTimeUtil();
            Logger logger = new Logger(PlatformSerializer.class);
            PlatformSerializer service = new PlatformSerializer(claimMapSerializer, localDateTimeUtil);
            scoped.put(PlatformSerializer.class, service);
            return service;
        }
    }

    public ClaimSerializer getClaimSerializer() {
        if(scoped.containsKey(ClaimSerializer.class)) {
            return (ClaimSerializer) scoped.get(ClaimSerializer.class);
        } else {
            Logger logger = new Logger(ClaimSerializer.class);
            ClaimSerializer service = new ClaimSerializer();
            scoped.put(ClaimSerializer.class, service);
            return service;
        }
    }

    public ClaimMapSerializer getClaimMapSerializer() {
        if(scoped.containsKey(ClaimMapSerializer.class)) {
            return (ClaimMapSerializer) scoped.get(ClaimMapSerializer.class);
        } else {
            Logger logger = new Logger(ClaimMapSerializer.class);
            ClaimMapSerializer service = new ClaimMapSerializer(getClaimSerializer(), logger);
            scoped.put(ClaimMapSerializer.class, service);
            return service;
        }
    }

    public AccountsRepo getAccountsRepo() {
        if(scoped.containsKey(AccountsRepo.class)) {
            return (AccountsRepo) scoped.get(AccountsRepo.class);
        } else {
            Logger logger = new Logger(AccountsRepo.class);
            AccountsRepo service = new AccountsRepo(getContext(), getAccountSerializer(), logger);
            scoped.put(AccountsRepo.class, service);
            return service;
        }
    }

    public PlatformsRepo getPlatformsRepo() {
        if(scoped.containsKey(PlatformsRepo.class)) {
            return (PlatformsRepo) scoped.get(PlatformsRepo.class);
        } else {
            Logger logger = new Logger(PlatformsRepo.class);
            PlatformsRepo service = new PlatformsRepo(getContext(), getPlatformSerializer(), logger);
            scoped.put(PlatformsRepo.class, service);
            return service;
        }
    }

    public void clearScope() {
        scoped.clear();
    }

}
