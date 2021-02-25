package models;

import models.utils.ModelSerializer;

public class SerializerFactory {

    private ModelSerializer<Account> accountSerializer;
    private ModelSerializer<Identity> identitySerializer;
    private ModelSerializer<Password> passwordSerializer;
    private ModelSerializer<Platform> platformSerializer;

    public ModelSerializer<Account> getAccountSerializer() {
        if(accountSerializer == null) {
            accountSerializer = new ModelSerializer<Account>(Account.class);
        }
        return accountSerializer;
    }

    public ModelSerializer<Identity> getIdentitySerializer() {
        if(identitySerializer == null) {
            identitySerializer = new ModelSerializer<Identity>(Identity.class);
        }
        return identitySerializer;
    }

    public ModelSerializer<Password> getPasswordSerializer() {
        if(passwordSerializer == null) {
            passwordSerializer = new ModelSerializer<Password>(Password.class);
        }
        return passwordSerializer;
    }

    public ModelSerializer<Platform> getPlatformSerializer() {
        if(platformSerializer == null) {
            platformSerializer = new ModelSerializer<Platform>(Platform.class);
        }
        return platformSerializer;
    }
    
}
