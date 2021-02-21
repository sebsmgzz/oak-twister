package serializers;

import models.Account;
import models.Password;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PasswordSerializer implements Serializer<Password> {

    @Override
    public Password serialize(Map<String, Object> map) {
        Password password = new Password();
        int id = (int) map.getOrDefault("id", -1);
        password.setId(id);
        int accountId = (int) map.getOrDefault("account", -1);
        AccountManager accountManager = new AccountManager();
        Account account = accountManager.select(accountId);
        password.setAccount(account);
        Date created = (Date) map.getOrDefault("created", null);
        password.setCreated(created);
        String value = (String) map.getOrDefault("value", null);
        password.setValue(value);
        // TODO: encrypt password value
        return password;
    }

    @Override
    public Map<String, Object> deserialize(Password password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", password.getId());
        map.put("account", password.getAccount().getId());
        map.put("created", password.getCreated());
        map.put("value", password.getValue());
        return map;
    }

}
