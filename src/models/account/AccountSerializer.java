package models.account;

import database.representations.QueryResult;
import models.Serializer;
import models.identity.Identity;
import models.platform.Platform;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

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
            return account;
        }
        return account;
    }

    @Override
    public Account serialize(HashMap<String, String> map) {
        Account account = new Account();
        try {
            account.setId(Integer.parseInt(map.getOrDefault("id", null)));
            account.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(map.getOrDefault("created", null)));
            account.setIdentity(new Identity());
            account.getIdentity().setId(Integer.parseInt(map.getOrDefault("identity", null)));
            account.setPlatform(new Platform());
            account.getPlatform().setId(Integer.parseInt(map.getOrDefault("platform", null)));
        } catch (Exception e) {
            return account;
        }
        return account;
    }

}
