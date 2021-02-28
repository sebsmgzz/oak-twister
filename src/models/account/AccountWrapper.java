package models.account;

import middleware.metadata.MetaModel;
import models.Wrapper;

public class AccountWrapper extends Wrapper {

    private MetaModel metaModel;
    private AccountSerializer serializer;
    private AccountManager manager;

    @Override
    public Account getData() {
        return new Account();
    }

    @Override
    public MetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new MetaModel(Account.class);
        }
        return metaModel;
    }

    @Override
    public AccountSerializer getSerializer() {
        if(serializer == null) {
            serializer = new AccountSerializer(getMetaModel(), this::getData);
        }
        return serializer;
    }

    @Override
    public AccountManager getManager() {
        if(manager == null) {
            manager = new AccountManager(getMetaModel(), getSerializer());
        }
        return manager;
    }

}
