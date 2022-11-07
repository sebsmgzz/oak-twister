package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.models.Account;
import com.oaktwister.domain.models.Platform;
import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.json.PlatformSerializer;
import com.oaktwister.app.services.logging.Logger;

import java.util.ArrayList;

public class PlatformsRepo extends JsonRepo<Platform> {

    public static final String LOCATION = "platforms";

    private final AccountsRepo accountsRepo;

    public PlatformsRepo(SessionSettings sessionSettings, PlatformSerializer platformSerializer,
                         AccountsRepo accountsRepo, Logger logger) {
        super(sessionSettings, platformSerializer, logger);
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
