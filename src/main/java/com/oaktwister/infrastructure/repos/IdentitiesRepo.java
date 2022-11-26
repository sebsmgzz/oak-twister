package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.domain.models.identities.Identity;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.infrastructure.serializers.IdentitySerializer;
import com.oaktwister.app.services.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class IdentitiesRepo extends JsonRepo<Identity> {

    public static final String LOCATION = "identities";

    private final AccountsRepo accountsRepo;

    public IdentitiesRepo(Session session, AccountsRepo accountsRepo,
                          IdentitySerializer identitySerializer, Logger logger) {
        super(session, identitySerializer, logger);
        this.accountsRepo = accountsRepo;
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

    @Override
    public void remove(Identity identity) throws IOException, InvalidSessionPropertyException {
        super.remove(identity);
        ArrayList<Account> accounts = accountsRepo.findByIdentity(identity);
        for(Account account : accounts) {
            account.setIdentityId(null);
            accountsRepo.update(account);
        }
    }

    @Override
    public boolean tryRemove(Identity identity) {
        boolean removed = super.tryRemove(identity);
        if(removed) {
            ArrayList<Account> accounts = accountsRepo.tryFindByIdentity(identity);
            for(Account account : accounts) {
                account.setIdentityId(null);
                boolean accountUpdated = accountsRepo.tryUpdate(account);
                if(!accountUpdated) {
                    return false;
                }
            }
        }
        return removed;
    }

}
