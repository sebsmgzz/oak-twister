package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.domain.models.platforms.Platform;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.infrastructure.serializers.PlatformSerializer;
import com.oaktwister.app.services.logging.Logger;

import java.util.ArrayList;

public class PlatformsRepo extends JsonRepo<Platform> {

    public static final String LOCATION = "platforms";

    private final AccountsRepo accountsRepo;

    public PlatformsRepo(Session session, PlatformSerializer platformSerializer,
                         AccountsRepo accountsRepo, Logger logger) {
        super(session, platformSerializer, logger);
        this.accountsRepo = accountsRepo;
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

    @Override
    public boolean remove(Platform platform) {
        boolean removed = super.remove(platform);
        if(removed) {
            ArrayList<Account> accounts = accountsRepo.findByPlatform(platform);
            for(Account account : accounts) {
                accountsRepo.remove(account);
            }
        }
        return removed;
    }

}
