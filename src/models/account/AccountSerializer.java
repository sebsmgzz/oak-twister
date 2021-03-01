package models.account;

import database.representations.QueryResult;
import models.Serializer;
import models.identity.Identity;
import models.platform.Platform;

public class AccountSerializer extends Serializer<Account> {

    @Override
    public Account serialize(QueryResult result) {
        Account account = new Account();
        try {
            account.setId(result.getInt("id"));
            account.setCreated(result.getDate("created"));
            account.setIdentity(new Identity());
            account.getIdentity().setId(result.getInt("identity"));
            account.setPlatform(new Platform());
            account.getPlatform().setId(result.getInt("platform"));
        } catch (Exception e) {
            return null;
        }
        return account;
    }

}
