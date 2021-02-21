package serializers;

import models.Account;
import models.Platform;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccountSerializer implements Serializer<Account> {


    public Account serialize(Map<String, Object> map) {
        Account account = new Account();
        int id = (int) map.getOrDefault("id", -1);
        account.setId(id);
        int platformId = map.getOrDefault("platform", -1);
        PlatformManager platformManager = new PlatformManager();
        Platform platform = platformManager.select(platformId);
        account.setPlatform(platform);
        int identityId = map.getOrDefault("identity", -1);
        IdentityManager identityManager = new IdentityManager();
        Identity identity = identityManager.select(identityId);
        account.setIdentity(identity);
        Date date = (Date) map.getOrDefault("created", null);
        account.setCreated(date);
    }

    public Map<String, Object> deserialize(Account account) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", account.getId());
        map.put("platform", account.getPlatform().getId());
        map.put("identity", account.getIdentity().getId());
        map.put("created", account.getCreated());
        return map;
    }

}
