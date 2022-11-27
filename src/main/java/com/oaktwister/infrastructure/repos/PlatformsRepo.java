package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.domain.models.platforms.Platform;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.infrastructure.serializers.PlatformSerializer;
import com.oaktwister.app.services.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;

public class PlatformsRepo extends JsonRepo<Platform> {

    public static final String DIRECTORY_NAME = "platforms";

    private final AccountsRepo accountsRepo;

    public PlatformsRepo(Session session, PlatformSerializer platformSerializer,
                         AccountsRepo accountsRepo, Logger logger) {
        super(session, platformSerializer, logger);
        this.accountsRepo = accountsRepo;
    }

    @Override
    protected String getRepoDirectoryName() {
        return DIRECTORY_NAME;
    }

    @Override
    public void remove(Platform platform) throws IOException, InvalidSessionPropertyException {
        super.remove(platform);
        ArrayList<Account> accounts = accountsRepo.findByPlatform(platform);
        for(Account account : accounts) {
            accountsRepo.remove(account);
        }
    }
    @Override
    public boolean tryRemove(Platform platform) {
        boolean removed = super.tryRemove(platform);
        if(removed) {
            ArrayList<Account> accounts = accountsRepo.tryFindByPlatform(platform);
            for(Account account : accounts) {
                boolean accountRemoved = accountsRepo.tryRemove(account);
                if(!accountRemoved) {
                    return false;
                }
            }
        }
        return removed;
    }

}
