package models.password;

import models.BaseManager;

public class PasswordManager extends BaseManager<Password> {

    public PasswordManager(PasswordMetaModel metaModel, PasswordSerializer serializer) {
        super(metaModel, serializer);
    }

    public Password selectById(int id) {
        return super.select("id", id);
    }

}
