package com.oaktwister.services.repos;

import com.oaktwister.models.Account;
import com.oaktwister.models.Identity;
import com.oaktwister.models.Platform;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.json.PlatformSerializer;
import com.oaktwister.services.logging.Logger;

import java.util.ArrayList;

public class PlatformsRepo extends JsonRepo<Platform> {

    public static final String LOCATION = "platforms";

    private final AccountsRepo accountsRepo;

    public PlatformsRepo(Context context, PlatformSerializer platformSerializer,
                         AccountsRepo accountsRepo, Logger logger) {
        super(context, platformSerializer, logger);
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
