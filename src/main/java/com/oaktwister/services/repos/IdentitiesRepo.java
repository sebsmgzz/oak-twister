package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.json.IdentitySerializer;
import com.oaktwister.services.logging.Logger;

import java.util.ArrayList;

public class IdentitiesRepo extends JsonRepo<Identity> {

    public static final String LOCATION = "identities";

    private final AccountsRepo accountsRepo;

    public IdentitiesRepo(Context context, AccountsRepo accountsRepo,
                          IdentitySerializer identitySerializer, Logger logger) {
        super(context, identitySerializer, logger);
        this.accountsRepo = accountsRepo;
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

    @Override
    public boolean remove(Identity entity) {
        boolean removed = super.remove(entity);
        if(removed) {
            ArrayList<Account> accounts = accountsRepo.findByIdentity(entity);
            for(Account account : accounts) {
                account.setIdentityId(null);
                accountsRepo.update(account);
            }
        }
        return removed;
    }

}
