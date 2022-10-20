package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.json.AccountSerializer;
import com.oaktwister.services.logging.Logger;

public class AccountsRepo extends JsonRepo<Account> {

    public static final String LOCATION = "accounts";

    public AccountsRepo(Context context, AccountSerializer accountSerializer, Logger logger) {
        super(context, accountSerializer, logger);
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}

