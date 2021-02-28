package models;

import models.account.AccountFactory;
import models.identity.IdentityFactory;
import models.password.PasswordFactory;
import models.platform.PlatformFactory;

public class ModelFactory {

    private final AccountFactory accountFactory;
    private final IdentityFactory identityFactory;
    private final PasswordFactory passwordFactory;
    private final PlatformFactory platformFactory;

    public BaseDataModel getDataModel(ModelType type) {
        switch (type) {
            case ACCOUNT:
                return accountFactory.getDataModel();
            case IDENTITY:
                return identityFactory.getDataModel();
            case PASSWORD:
                return passwordFactory.getDataModel();
            case PLATFORM:
                return platformFactory.getDataModel();
            default:
                return null;
        }
    }

    public BaseManager<?> getManager(ModelType type) {
        switch (type) {
            case ACCOUNT:
                return accountFactory.getManager();
            case IDENTITY:
                return identityFactory.getManager();
            case PASSWORD:
                return passwordFactory.getManager();
            case PLATFORM:
                return platformFactory.getManager();
            default:
                return null;
        }
    }

    public BaseSerializer<?> getSerializer(ModelType type){
        switch (type) {
            case ACCOUNT:
                return accountFactory.getSerializer();
            case IDENTITY:
                return identityFactory.getSerializer();
            case PASSWORD:
                return passwordFactory.getSerializer();
            case PLATFORM:
                return platformFactory.getSerializer();
            default:
                return null;
        }
    }

    public ModelFactory() {
        this.accountFactory = new AccountFactory();
        this.identityFactory = new IdentityFactory();
        this.passwordFactory = new PasswordFactory();
        this.platformFactory = new PlatformFactory();
    }

}
