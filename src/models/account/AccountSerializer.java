package models.account;

import middleware.metadata.MetaModel;
import models.Serializer;

import java.util.concurrent.Callable;

public class AccountSerializer extends Serializer<Account> {

    public AccountSerializer(MetaModel metaModel, Callable<Account> factory) {
        super(metaModel, factory);
    }

}
