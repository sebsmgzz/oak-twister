package models.identity;

import managers.IdentityManager;
import models.Wrapper;

public class IdentityWrapper extends Wrapper {

    private IdentitySerializer serializer;
    private IdentityManager manager;

    @Override
    public Identity getData() {
        return new Identity();
    }

    @Override
    public IdentitySerializer getSerializer() {
        if(serializer == null) {
            serializer = new IdentitySerializer();
        }
        return serializer;
    }

    @Override
    public IdentityManager getManager() {
        if(manager == null) {
            manager = new IdentityManager(getSerializer());
        }
        return manager;
    }

}
