package models;

import models.account.AccountSerializer;
import models.identity.IdentitySerializer;
import models.password.PasswordSerializer;
import models.platform.PlatformSerializer;

public class SerializerFactory {

    private AccountSerializer accountSerializer;
    private IdentitySerializer identitySerializer;
    private PasswordSerializer passwordSerializer;
    private PlatformSerializer platformSerializer;

    public AccountSerializer getAccountSerializer() {
        if(accountSerializer == null) {
            accountSerializer = new AccountSerializer();
        }
        return accountSerializer;
    }

    public IdentitySerializer getIdentitySerializer() {
        if(identitySerializer == null) {
            identitySerializer = new IdentitySerializer();
        }
        return identitySerializer;
    }

    public PasswordSerializer getPasswordSerializer() {
        if(passwordSerializer == null) {
            passwordSerializer = new PasswordSerializer();
        }
        return passwordSerializer;
    }

    public PlatformSerializer getPlatformSerializer() {
        if(platformSerializer == null) {
            platformSerializer = new PlatformSerializer();
        }
        return platformSerializer;
    }

}
