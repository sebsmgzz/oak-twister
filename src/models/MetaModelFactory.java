package models;

import models.account.Account;
import models.identity.Identity;
import models.metamodels.MetaModel;
import models.password.Password;
import models.platform.Platform;

public class MetaModelFactory {
    
    private MetaModel accountMetaModel;
    private MetaModel identityMetaModel;
    private MetaModel passwordMetaModel;
    private MetaModel platformMetaModel;

    public MetaModel getAccountMetaModel() {
        if(accountMetaModel == null) {
            accountMetaModel = new MetaModel(Account.class);
        }
        return accountMetaModel;
    }

    public MetaModel getIdentityMetaModel() {
        if(identityMetaModel == null) {
            identityMetaModel = new MetaModel(Identity.class);
        }
        return identityMetaModel;
    }

    public MetaModel getPasswordMetaModel() {
        if(passwordMetaModel == null) {
            passwordMetaModel = new MetaModel(Password.class);
        }
        return passwordMetaModel;
    }

    public MetaModel getPlatformMetaModel() {
        if(platformMetaModel == null) {
            platformMetaModel = new MetaModel(Platform.class);
        }
        return platformMetaModel;
    }


}
