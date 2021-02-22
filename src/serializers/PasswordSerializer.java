package serializers;

import managers.AccountManager;
import models.Account;
import models.Password;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PasswordSerializer extends BaseSerializer {

    private final Callable<Password> modelFactory;
    private final AccountManager accountManager;

    public PasswordSerializer(Callable<Password> modelFactory, AccountManager accountManager) {
        this.modelFactory = modelFactory;
        this.accountManager = accountManager;
    }

    public Password serialize(Map<String, Object> map) throws Exception {
        Password password = modelFactory.call();
        int id = (int) map.getOrDefault("id", -1);
        password.setId(id);
        int accountId = (int) map.getOrDefault("account", -1);
        Account account = accountManager.select(accountId);
        password.setAccount(account);
        Date created = (Date) map.getOrDefault("created", null);
        password.setCreated(created);
        String value = (String) map.getOrDefault("value", null);
        password.setValue(value);
        // TODO: encrypt password value
        return password;
    }

    public Map<String, Object> deserialize(Password password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", password.getId());
        map.put("account", password.getAccount().getId());
        map.put("created", password.getCreated());
        map.put("value", password.getValue());
        return map;
    }

}
