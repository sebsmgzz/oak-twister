package models;

import models.account.AccountManager;
import models.identity.IdentityManager;
import models.password.PasswordManager;
import models.platform.PlatformManager;

public class ManagerFactory {

    private final MetaModelFactory metaModelFactory;
    private final SerializerFactory serializerFactory;

    private AccountManager accountManager;
    private IdentityManager identityManager;
    private PasswordManager passwordManager;
    private PlatformManager platformManager;

    public AccountManager getAccountManager() {
        if(accountManager == null) {
            accountManager = new AccountManager(
                    metaModelFactory.getAccountMetaModel(),
                    serializerFactory.getAccountSerializer());
        }
        return accountManager;
    }

    public IdentityManager getIdentityManager() {
        if(identityManager == null) {
            identityManager = new IdentityManager(
                    metaModelFactory.getIdentityMetaModel(),
                    serializerFactory.getIdentitySerializer());
        }
        return identityManager;
    }

    public PasswordManager getPasswordManager() {
        if(passwordManager == null) {
            passwordManager = new PasswordManager(
                    metaModelFactory.getPasswordMetaModel(),
                    serializerFactory.getPasswordSerializer());
        }
        return passwordManager;
    }

    public PlatformManager getPlatformManager() {
        if(platformManager == null) {
            platformManager = new PlatformManager(
                    metaModelFactory.getPlatformMetaModel(),
                    serializerFactory.getPlatformSerializer());
        }
        return platformManager;
    }

    public ManagerFactory(MetaModelFactory metaModelFactory, SerializerFactory serializerFactory) {
        this.metaModelFactory = metaModelFactory;
        this.serializerFactory = serializerFactory;
    }

}
