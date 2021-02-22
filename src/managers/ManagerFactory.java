package managers;

import serializers.SerializerFactory;

public class ManagerFactory {

    private final SerializerFactory serializerFactory;

    public ManagerFactory(SerializerFactory serializerFactory) {
        this.serializerFactory = serializerFactory;
    }

    public AccountManager getAccountManager() {
        return new AccountManager(serializerFactory.getAccountSerializer());
    }

    public IdentityManager getIdentityManager() {
        return new IdentityManager(serializerFactory.getIdentitySerializer());
    }

    public PasswordManager getPasswordManager() {
        return new PasswordManager(serializerFactory.getPasswordSerializer());
    }

    public PlatformManager getPlatformManager() {
        return new PlatformManager(serializerFactory.getPlatformSerializer());
    }

}
