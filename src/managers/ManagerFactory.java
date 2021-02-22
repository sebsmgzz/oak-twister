package managers;

import metamodels.MetaModel;
import models.Account;
import models.Identity;
import models.Password;
import models.Platform;
import serializers.SerializerFactory;

public class ManagerFactory {

    private final SerializerFactory serializerFactory;

    private AccountManager accountManager;
    private IdentityManager identityManager;
    private PasswordManager passwordManager;
    private PlatformManager platformManager;


    public ManagerFactory(SerializerFactory serializerFactory) {
        this.serializerFactory = serializerFactory;
    }

    public AccountManager getAccountManager() {
        if(accountManager == null) {
            accountManager = new AccountManager(
                    new MetaModel(Account.class),
                    serializerFactory.getAccountSerializer());
        }
        return accountManager;
    }

    public IdentityManager getIdentityManager() {
        if(identityManager == null) {
            identityManager = new IdentityManager(
                    new MetaModel(Identity.class),
                    serializerFactory.getIdentitySerializer());
        }
        return identityManager;
    }

    public PasswordManager getPasswordManager() {
        if(passwordManager == null) {
            passwordManager = new PasswordManager(
                    new MetaModel(Password.class),
                    serializerFactory.getPasswordSerializer());
        }
        return passwordManager;
    }

    public PlatformManager getPlatformManager() {
        if(platformManager == null) {
            platformManager = new PlatformManager(
                    new MetaModel(Platform.class),
                    serializerFactory.getPlatformSerializer());
        }
        return platformManager;
    }

}
