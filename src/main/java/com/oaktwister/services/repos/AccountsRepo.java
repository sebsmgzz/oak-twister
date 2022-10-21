package com.oaktwister.services.repos;

import com.oaktwister.models.Account;
import com.oaktwister.models.Identity;
import com.oaktwister.models.Platform;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.json.AccountSerializer;
import com.oaktwister.services.logging.Logger;

import java.util.ArrayList;

public class AccountsRepo extends JsonRepo<Account> {

    public static final String LOCATION = "accounts";

    public AccountsRepo(Context context, AccountSerializer accountSerializer, Logger logger) {
        super(context, accountSerializer, logger);
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

