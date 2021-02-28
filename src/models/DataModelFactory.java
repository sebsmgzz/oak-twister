package models;

import models.account.Account;
import models.identity.Identity;
import models.password.Password;
import models.platform.Platform;

public class DataModelFactory {

    public Account getAccount() {
        return new Account();
    }

    public Identity getIdentity() {
        return new Identity();
    }

    public Password getPassword() {
        return new Password();
    }

    public Platform getPlatform() {
        return new Platform();
    }

}
