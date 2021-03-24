package models.account;

import managers.AccountManager;
import models.Wrapper;

public class AccountWrapper extends Wrapper {

    private AccountSerializer serializer;
    private AccountManager manager;

    @Override
    public Account getData() {
        return new Account();
    }

    @Override
    public AccountSerializer getSerializer() {
        if(serializer == null) {
            serializer = new AccountSerializer();
        }
        return serializer;
    }

    @Override
    public AccountManager getManager() {
        if(manager == null) {
            manager = new AccountManager(getSerializer());
        }
        return manager;
    }

}
