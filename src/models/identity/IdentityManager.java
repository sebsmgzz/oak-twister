package models.identity;

import models.BaseManager;

public class IdentityManager extends BaseManager<Identity> {

    public IdentityManager(IdentityMetaModel metaModel, IdentitySerializer serializer) {
        super(metaModel, serializer);
    }

    public Identity selectById(int id) {
        return super.select("id", id);
    }

}
