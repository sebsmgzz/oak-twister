package serializers;

import managers.IdentityManager;
import managers.PlatformManager;
import models.Account;
import models.Identity;
import models.Platform;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class AccountSerializer extends BaseSerializer {

    private final Callable<Account> modelFactory;
    private final PlatformManager platformManager;
    private final IdentityManager identityManager;

    public AccountSerializer(Callable<Account> modelFactory, PlatformManager platformManager, IdentityManager identityManager) {
        this.modelFactory = modelFactory;
        this.platformManager = platformManager;
        this.identityManager = identityManager;
    }

    public Account serialize(Map<String, Object> map) throws Exception {
        Account account = modelFactory.call();
        int id = (int) map.getOrDefault("id", -1);
        account.setId(id);
        int platformId = (int)map.getOrDefault("platform", -1);
        Platform platform = platformManager.select(platformId);
        account.setPlatform(platform);
        int identityId = (int)map.getOrDefault("identity", -1);
        Identity identity = identityManager.select(identityId);
        account.setIdentity(identity);
        Date date = (Date) map.getOrDefault("created", null);
        account.setCreated(date);
        return account;
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
