package com.oaktwister.app.core;

import com.oaktwister.app.services.configs.Environment;
import com.oaktwister.app.services.configs.AppSettings;
import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.drives.DriveLoader;
import com.oaktwister.app.services.json.AccountSerializer;
import com.oaktwister.app.services.json.ClaimMapSerializer;
import com.oaktwister.app.services.json.ClaimSerializer;
import com.oaktwister.app.services.json.GrantMapSerializer;
import com.oaktwister.app.services.json.GrantSerializer;
import com.oaktwister.app.services.json.IdentitySerializer;
import com.oaktwister.app.services.json.PlatformSerializer;
import com.oaktwister.app.services.parsers.GrantTypeParser;
import com.oaktwister.app.services.logging.Logger;
import com.oaktwister.infrastructure.repos.AccountsRepo;
import com.oaktwister.infrastructure.repos.IdentitiesRepo;
import com.oaktwister.infrastructure.repos.ImagesRepo;
import com.oaktwister.infrastructure.repos.PlatformsRepo;

import java.util.HashMap;

public class ServiceFactory {

    private final HashMap<Class<?>, Object> singletons = new HashMap<>();
    private final HashMap<Class<?>, Object> scoped = new HashMap<>();

    public <T> Logger getLogger(Class<T> loggerClass) {
        return new Logger(loggerClass);
    }

    public SessionSettings getSessionSettings() {
        if(singletons.containsKey(SessionSettings.class)) {
            return (SessionSettings) singletons.get(SessionSettings.class);
        } else {
            SessionSettings service = new SessionSettings();
            singletons.put(SessionSettings.class, service);
            return service;
        }
    }

    public AppSettings getAppConfig() {
        if(singletons.containsKey(AppSettings.class)) {
            return (AppSettings) singletons.get(AppSettings.class);
        } else {
            AppSettings service = AppSettings.getInstance();
            singletons.put(AppSettings.class, service);
            return service;
        }
    }

    public Environment getEnvironment() {
        if(singletons.containsKey(Environment.class)) {
            return (Environment) singletons.get(Environment.class);
        } else {
            Environment service = Environment.getInstance();
            singletons.put(Environment.class, service);
            return service;
        }
    }

    public DriveLoader getDriveLoader() {
        if(scoped.containsKey(DriveLoader.class)) {
            return (DriveLoader) scoped.get(DriveLoader.class);
        } else {
            Environment environment = getEnvironment();
            Logger logger = getLogger(DriveLoader.class);
            DriveLoader service = new DriveLoader(environment);
            scoped.put(DriveLoader.class, service);
            return service;
        }
    }

    public GrantTypeParser getGrantTypeParser() {
        if(scoped.containsKey(GrantTypeParser.class)) {
            return (GrantTypeParser) scoped.get(GrantTypeParser.class);
        } else {
            Logger logger = getLogger(GrantTypeParser.class);
            GrantTypeParser service = new GrantTypeParser();
            scoped.put(GrantTypeParser.class, service);
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
            Logger logger = getLogger(GrantMapSerializer.class);
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
            SessionSettings sessionSettings = getSessionSettings();
            Logger logger = getLogger(ImagesRepo.class);
            ImagesRepo service = new ImagesRepo(sessionSettings, logger);
            scoped.put(ImagesRepo.class, service);
            return service;
        }
    }

    public IdentitiesRepo getIdentitiesRepo() {
        if(scoped.containsKey(IdentitiesRepo.class)) {
            return (IdentitiesRepo) scoped.get(IdentitiesRepo.class);
        } else {
            SessionSettings sessionSettings = getSessionSettings();
            AccountsRepo accountsRepo = getAccountsRepo();
            IdentitySerializer identitySerializer = getIdentitySerializer();
            Logger logger = getLogger(IdentitiesRepo.class);
            IdentitiesRepo service = new IdentitiesRepo(sessionSettings, accountsRepo, identitySerializer, logger);
            scoped.put(IdentitiesRepo.class, service);
            return service;
        }
    }

    public PlatformSerializer getPlatformSerializer() {
        if(scoped.containsKey(PlatformSerializer.class)) {
            return (PlatformSerializer) scoped.get(PlatformSerializer.class);
        } else {
            ClaimMapSerializer claimMapSerializer = getClaimMapSerializer();
            Logger logger = getLogger(PlatformSerializer.class);
            PlatformSerializer service = new PlatformSerializer(claimMapSerializer, logger);
            scoped.put(PlatformSerializer.class, service);
            return service;
        }
    }

    public ClaimSerializer getClaimSerializer() {
        if(scoped.containsKey(ClaimSerializer.class)) {
            return (ClaimSerializer) scoped.get(ClaimSerializer.class);
        } else {
            Logger logger = getLogger(ClaimSerializer.class);
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
            Logger logger = getLogger(ClaimMapSerializer.class);
            ClaimMapSerializer service = new ClaimMapSerializer(claimSerializer, logger);
            scoped.put(ClaimMapSerializer.class, service);
            return service;
        }
    }

    public AccountsRepo getAccountsRepo() {
        if(scoped.containsKey(AccountsRepo.class)) {
            return (AccountsRepo) scoped.get(AccountsRepo.class);
        } else {
            SessionSettings sessionSettings = getSessionSettings();
            AccountSerializer accountSerializer = getAccountSerializer();
            Logger logger = getLogger(AccountsRepo.class);
            AccountsRepo service = new AccountsRepo(sessionSettings, accountSerializer, logger);
            scoped.put(AccountsRepo.class, service);
            return service;
        }
    }

    public PlatformsRepo getPlatformsRepo() {
        if(scoped.containsKey(PlatformsRepo.class)) {
            return (PlatformsRepo) scoped.get(PlatformsRepo.class);
        } else {
            SessionSettings sessionSettings = getSessionSettings();
            PlatformSerializer platformSerializer = getPlatformSerializer();
            AccountsRepo accountsRepo = getAccountsRepo();
            Logger logger = getLogger(PlatformsRepo.class);
            PlatformsRepo service = new PlatformsRepo(sessionSettings, platformSerializer, accountsRepo, logger);
            scoped.put(PlatformsRepo.class, service);
            return service;
        }
    }

    public void clearScope() {
        scoped.clear();
    }

}
