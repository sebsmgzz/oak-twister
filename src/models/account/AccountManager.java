package models.account;

import middleware.metadata.MetaModel;
import models.Manager;

public class AccountManager extends Manager<Account> {

    public AccountManager(MetaModel metaModel, AccountSerializer serializer) {
        super(metaModel, serializer);
    }

    public Account selectById(int id) {
        return super.selectWhere("id", id);
    }

}
