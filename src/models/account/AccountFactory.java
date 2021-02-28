package models.account;

public class AccountFactory {

    private AccountMetaModel metaModel;
    private AccountSerializer serializer;
    private AccountManager manager;

    public Account getDataModel() {
        return new Account();
    }

    public AccountMetaModel getMetaModel() {
        if(metaModel == null) {
            metaModel = new AccountMetaModel();
        }
        return metaModel;
    }

    public AccountSerializer getSerializer() {
        if(serializer == null) {
            serializer = new AccountSerializer();
        }
        return serializer;
    }

    public AccountManager getManager() {
        if(manager == null) {
            manager = new AccountManager(getMetaModel(), getSerializer());
        }
        return manager;
    }

}
