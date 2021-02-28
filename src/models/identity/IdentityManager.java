package models.identity;

import middleware.metadata.MetaModel;
import models.Manager;

public class IdentityManager extends Manager<Identity> {

    public IdentityManager(MetaModel metaModel, IdentitySerializer serializer) {
        super(metaModel, serializer);
    }

    public Identity selectById(int id) {
        return selectWhere("id", id);
    }

}
