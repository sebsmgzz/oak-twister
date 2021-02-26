package models;

import models.utils.ModelManager;

public class ManagerFactory {

    private final MetaModelFactory metaModelFactory;

    private ModelManager accountManager;
    private ModelManager identityManager;
    private ModelManager passwordManager;
    private ModelManager platformManager;

    public ManagerFactory(MetaModelFactory metaModelFactory) {
        this.metaModelFactory = metaModelFactory;
    }

    public ModelManager getAccountManager() {
        if(accountManager == null) {
            accountManager = new ModelManager(metaModelFactory.getAccountMetaModel());
        }
        return accountManager;
    }

    public ModelManager getIdentityManager() {
        if(identityManager == null) {
            identityManager = new ModelManager(metaModelFactory.getIdentityMetaModel());
        }
        return identityManager;
    }

    public ModelManager getPasswordManager() {
        if(passwordManager == null) {
            passwordManager = new ModelManager(metaModelFactory.getPasswordMetaModel());
        }
        return passwordManager;
    }

    public ModelManager getPlatformManager() {
        if(platformManager == null) {
            platformManager = new ModelManager(metaModelFactory.getPlatformMetaModel());
        }
        return platformManager;
    }

}
