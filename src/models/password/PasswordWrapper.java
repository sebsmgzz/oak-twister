package models.password;

import middleware.metadata.MetaModel;
import models.Wrapper;

public class PasswordWrapper extends Wrapper {

    private MetaModel metaModel;
    private PasswordSerializer serializer;
    private PasswordManager manager;

    @Override
    public Password getData() {
        return new Password();
    }

    @Override
    public MetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new MetaModel(Password.class);
        }
        return metaModel;
    }

    @Override
    public PasswordSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PasswordSerializer(getMetaModel(), this::getData);
        }
        return serializer;
    }

    @Override
    public PasswordManager getManager() {
        if(manager == null) {
            manager = new PasswordManager(getMetaModel(), getSerializer());
        }
        return manager;
    }

}
