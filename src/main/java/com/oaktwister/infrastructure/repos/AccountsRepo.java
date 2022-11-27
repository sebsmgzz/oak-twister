package com.oaktwister.infrastructure.repos;

import com.oaktwister.domain.exceptions.InvalidSessionPropertyException;
import com.oaktwister.domain.models.accounts.Account;
import com.oaktwister.domain.models.identities.Identity;
import com.oaktwister.domain.models.platforms.Platform;
import com.oaktwister.domain.services.configs.Session;
import com.oaktwister.infrastructure.serializers.AccountSerializer;
import com.oaktwister.app.services.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;

public class AccountsRepo extends JsonRepo<Account> {

    public static final String DIRECTORY_NAME = "accounts";

    public AccountsRepo(Session session, AccountSerializer accountSerializer, Logger logger) {
        super(session, accountSerializer, logger);
    }

    @Override
    protected String getRepoDirectoryName() {
        return DIRECTORY_NAME;
    }

    public ArrayList<Account> findByIdentity(Identity identity) throws IOException, InvalidSessionPropertyException {
        ArrayList<Account> accounts = findAll();
        accounts.removeIf(account -> !identity.getId().equals(account.getIdentityId()));
        return accounts;
    }
    public ArrayList<Account> tryFindByIdentity(Identity identity) {
        try {
            return findByIdentity(identity);
        } catch (IOException | InvalidSessionPropertyException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Account> findByPlatform(Platform platform) throws IOException, InvalidSessionPropertyException {
        ArrayList<Account> accounts = findAll();
        accounts.removeIf(account -> !platform.getId().equals(account.getPlatformId()));
        return accounts;
    }
    public ArrayList<Account> tryFindByPlatform(Platform platform) {
        try {
            return findByPlatform(platform);
        } catch (IOException | InvalidSessionPropertyException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}

