package serializers;

import managers.ManagerFactory;
import models.ModelFactory;

public class SerializerFactory {

    ModelFactory modelFactory;
    ManagerFactory managerFactory;

    public SerializerFactory(ModelFactory modelFactory, ManagerFactory managerFactory) {
        this.modelFactory = modelFactory;
        this.managerFactory = managerFactory;
    }

    public AccountSerializer getAccountSerializer() {
        return new AccountSerializer(modelFactory::getAccount, managerFactory.getPlatformManager(), managerFactory.getIdentityManager());
    }

    public IdentitySerializer getIdentitySerializer() {
        return new IdentitySerializer(modelFactory::getIdentity);
    }

    public PasswordSerializer getPasswordSerializer() {
        return new PasswordSerializer(modelFactory::getPassword, managerFactory.getAccountManager());
    }

    public PlatformSerializer getPlatformSerializer() {
        return new PlatformSerializer(modelFactory::getPlatform);
    }

}
