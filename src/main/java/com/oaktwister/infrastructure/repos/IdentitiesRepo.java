package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.domain.models.identities.Identity;
import com.oaktwister.app.services.configs.SessionSettings;
import com.oaktwister.app.services.json.IdentitySerializer;
import com.oaktwister.app.services.logging.Logger;

import java.util.ArrayList;

public class IdentitiesRepo extends JsonRepo<Identity> {

    public static final String LOCATION = "identities";

    private final AccountsRepo accountsRepo;

    public IdentitiesRepo(SessionSettings sessionSettings, AccountsRepo accountsRepo,
                          IdentitySerializer identitySerializer, Logger logger) {
        super(sessionSettings, identitySerializer, logger);
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
