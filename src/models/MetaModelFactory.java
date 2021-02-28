package models;

import models.account.AccountMetaModel;
import models.identity.IdentityMetaModel;
import models.password.PasswordMetaModel;
import models.platform.PlatformMetaModel;

public class MetaModelFactory {

    private AccountMetaModel accountMetaModel;
    private IdentityMetaModel identityMetaModel;
    private PasswordMetaModel passwordMetaModel;
    private PlatformMetaModel platformMetaModel;

    public AccountMetaModel getAccountMetaModel() {
        if(accountMetaModel == null) {
            accountMetaModel = new AccountMetaModel();
        }
        return accountMetaModel;
    }

    public IdentityMetaModel getIdentityMetaModel() {
        if(identityMetaModel == null) {
            identityMetaModel = new IdentityMetaModel();
        }
        return identityMetaModel;
    }

    public PasswordMetaModel getPasswordMetaModel() {
        if(passwordMetaModel == null) {
            passwordMetaModel = new PasswordMetaModel();
        }
        return passwordMetaModel;
    }

    public PlatformMetaModel getPlatformMetaModel() {
        if(platformMetaModel == null) {
            platformMetaModel = new PlatformMetaModel();
        }
        return platformMetaModel;
    }

}
