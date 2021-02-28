package models.password;

import middleware.metadata.MetaModel;
import models.Manager;

public class PasswordManager extends Manager<Password> {

    public PasswordManager(MetaModel metaModel, PasswordSerializer serializer) {
        super(metaModel, serializer);
    }

    public Password selectById(int id) {
        return selectWhere("id", id);
    }

}
