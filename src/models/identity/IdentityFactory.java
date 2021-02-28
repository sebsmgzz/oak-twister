package models.identity;

public class IdentityFactory {

    private IdentityMetaModel metaModel;
    private IdentitySerializer serializer;
    private IdentityManager manager;

    public Identity getDataModel() {
        return new Identity();
    }

    public IdentityMetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new IdentityMetaModel();
        }
        return metaModel;
    }

    public IdentitySerializer getSerializer() {
        if(serializer == null) {
            serializer = new IdentitySerializer();
        }
        return serializer;
    }

    public IdentityManager getManager() {
        if(manager == null) {
            manager = new IdentityManager(getMetaModel(), getSerializer());
        }
        return manager;
    }
    
}
