package models;

import models.utils.ModelManager;

public class ManagerFactory {

    private ModelManager<Account> accountManager;
    private ModelManager<Identity> identityManager;
    private ModelManager<Password> passwordManager;
    private ModelManager<Platform> platformManager;

    public ModelManager<Account> getAccountManager() {
        if(accountManager == null) {
            accountManager = new ModelManager<Account>(Account.class);
        }
        return accountManager;
    }

    public ModelManager<Identity> getIdentityManager() {
        if(identityManager == null) {
            identityManager = new ModelManager<Identity>(Identity.class);
        }
        return identityManager;
    }

    public ModelManager<Password> getPasswordManager() {
        if(passwordManager == null) {
            passwordManager = new ModelManager<Password>(Password.class);
        }
        return passwordManager;
    }

    public ModelManager<Platform> getPlatformManager() {
        if(platformManager == null) {
            platformManager = new ModelManager<Platform>(Platform.class);
        }
        return platformManager;
    }

}
