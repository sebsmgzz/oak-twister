package models;

import models.account.AccountSerializer;
import models.identity.IdentitySerializer;
import models.password.PasswordSerializer;
import models.platform.PlatformSerializer;

public class SerializerFactory {

    private ModelFactory modelFactory;
    private MetaModelFactory metaModelFactory;

    private AccountSerializer accountSerializer;
    private IdentitySerializer identitySerializer;
    private PasswordSerializer passwordSerializer;
    private PlatformSerializer platformSerializer;

    public AccountSerializer getAccountSerializer() {
        if(accountSerializer == null) {
            accountSerializer = new AccountSerializer(
                    modelFactory::getAccount,
                    metaModelFactory.getAccountMetaModel());
        }
        return accountSerializer;
    }

    public IdentitySerializer getIdentitySerializer() {
        if(identitySerializer == null) {
            identitySerializer = new IdentitySerializer(
                    modelFactory::getIdentity,
                    metaModelFactory.getIdentityMetaModel());
        }
        return identitySerializer;
    }

    public PasswordSerializer getPasswordSerializer() {
        if(passwordSerializer == null) {
            passwordSerializer = new PasswordSerializer(
                    modelFactory::getPassword,
                    metaModelFactory.getPasswordMetaModel());
        }
        return passwordSerializer;
    }

    public PlatformSerializer getPlatformSerializer() {
        if(platformSerializer == null) {
            platformSerializer = new PlatformSerializer(
                    modelFactory::getPlatform,
                    metaModelFactory.getPlatformMetaModel());
        }
        return platformSerializer;
    }
    
}
