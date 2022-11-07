package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.models.Account;
import com.oaktwister.domain.models.Identity;
import com.oaktwister.domain.models.Platform;
import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.json.AccountSerializer;
import com.oaktwister.app.services.logging.Logger;

import java.util.ArrayList;

public class AccountsRepo extends JsonRepo<Account> {

    public static final String LOCATION = "accounts";

    public AccountsRepo(SessionSettings sessionSettings, AccountSerializer accountSerializer, Logger logger) {
        super(sessionSettings, accountSerializer, logger);
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

    public ArrayList<Account> findByIdentity(Identity identity) {
        ArrayList<Account> accounts = findAll();
        accounts.removeIf(account -> !identity.getId().equals(account.getIdentityId()));
        return accounts;
    }

    public ArrayList<Account> findByPlatform(Platform platform) {
        ArrayList<Account> accounts = findAll();
        accounts.removeIf(account -> !platform.getId().equals(account.getPlatformId()));
        return accounts;
    }

}

