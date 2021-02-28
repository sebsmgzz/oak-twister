package models;

import models.account.AccountWrapper;
import models.identity.IdentityWrapper;
import models.password.PasswordWrapper;
import models.platform.PlatformWrapper;

public final class WrapperFactory {

    private AccountWrapper accountWrapper;
    private IdentityWrapper identityWrapper;
    private PasswordWrapper passwordWrapper;
    private PlatformWrapper platformWrapper;

    public AccountWrapper getAccount() {
        if(accountWrapper == null) {
            accountWrapper = new AccountWrapper();
        }
        return accountWrapper;
    }

    public IdentityWrapper getIdentity() {
        if(identityWrapper == null) {
            identityWrapper = new IdentityWrapper();
        }
        return identityWrapper;
    }

    public PasswordWrapper getPassword() {
        if(passwordWrapper == null) {
            passwordWrapper = new PasswordWrapper();
        }
        return passwordWrapper;
    }

    public PlatformWrapper getPlatform() {
        if(platformWrapper == null) {
            platformWrapper = new PlatformWrapper();
        }
        return platformWrapper;
    }

}
