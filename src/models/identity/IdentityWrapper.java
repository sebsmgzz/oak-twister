package models.identity;

import middleware.metadata.MetaModel;
import models.Wrapper;

public class IdentityWrapper extends Wrapper {

    private MetaModel metaModel;
    private IdentitySerializer serializer;
    private IdentityManager manager;

    @Override
    public Identity getData() {
        return new Identity();
    }

    @Override
    public MetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new MetaModel(Identity.class);
        }
        return metaModel;
    }

    @Override
    public IdentitySerializer getSerializer() {
        if(serializer == null) {
            serializer = new IdentitySerializer(getMetaModel(), this::getData);
        }
        return serializer;
    }

    @Override
    public IdentityManager getManager() {
        if(manager == null) {
            manager = new IdentityManager(getMetaModel(), getSerializer());
        }
        return manager;
    }

}
