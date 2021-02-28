package models.password;


public class PasswordFactory {

    private PasswordMetaModel metaModel;
    private PasswordSerializer serializer;
    private PasswordManager manager;

    public Password getDataModel() {
        return new Password();
    }

    public PasswordMetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new PasswordMetaModel();
        }
        return metaModel;
    }

    public PasswordSerializer getSerializer() {
        if(serializer == null) {
            serializer = new PasswordSerializer();
        }
        return serializer;
    }

    public PasswordManager getManager() {
        if(manager == null) {
            manager = new PasswordManager(getMetaModel(), getSerializer());
        }
        return manager;
    }
    
}
