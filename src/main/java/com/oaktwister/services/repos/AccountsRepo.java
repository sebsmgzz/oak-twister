package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.services.Context;
import com.oaktwister.services.json.AccountSerializer;

public class AccountsRepo extends JsonRepo<Account> {

    public static final String LOCATION = "accounts";

    public AccountsRepo(Context context) {
        super(context, new AccountSerializer());
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}

