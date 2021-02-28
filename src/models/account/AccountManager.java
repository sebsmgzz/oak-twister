package models.account;

import models.BaseManager;

public class AccountManager extends BaseManager<Account> {

    public AccountManager(AccountMetaModel metaModel, AccountSerializer serializer) {
        super(metaModel, serializer);
    }

    public Account selectById(int id) {
        return super.select("id", id);
    }

}
