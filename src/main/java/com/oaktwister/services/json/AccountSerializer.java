package com.oaktwister.services.json;

import com.oaktwister.models.Account;
import com.oaktwister.exceptions.UnknownGrantTypeException;
import com.oaktwister.utils.extensions.LocalDateTimeUtil;
import org.json.JSONObject;

import java.util.UUID;

public class AccountSerializer implements JsonObjectSerializer<Account> {

    private final static String ID_KEY = "id";
    private final static String PLATFORM_ID_KEY = "platformId";
    private final static String IDENTITY_ID_KEY = "identityId";
    private final static String CREATED_AT_KEY = "createdAt";
    private final static String GRANTS_KEY = "grants";

    private final GrantMapSerializer grantMapSerializer;
    private final LocalDateTimeUtil localDateTimeUtil;

    public AccountSerializer(GrantMapSerializer grantMapSerializer, LocalDateTimeUtil localDateTimeUtil) {
        this.grantMapSerializer = grantMapSerializer;
        this.localDateTimeUtil = localDateTimeUtil;
    }

    @Override
    public Account deserialize(JSONObject json) throws UnknownGrantTypeException {

        UUID identityId = null;
        if(!json.isNull(IDENTITY_ID_KEY)) {
            identityId = UUID.fromString(json.getString(IDENTITY_ID_KEY));
        }

        Account account = new Account(
            UUID.fromString(json.get(ID_KEY).toString()),
            UUID.fromString(json.get(PLATFORM_ID_KEY).toString()),
            identityId,
            localDateTimeUtil.fromIso8601(json.getString(CREATED_AT_KEY)));
        account.setGrants(grantMapSerializer.deserialize(json.getJSONArray(GRANTS_KEY)));
        return account;

    }

    @Override
    public JSONObject serialize(Account account) {
        JSONObject accountJson = new JSONObject();
        accountJson.put(ID_KEY, account.getId());
        accountJson.put(PLATFORM_ID_KEY, account.getPlatformId());
        UUID identityId = account.getIdentityId();
        accountJson.put(IDENTITY_ID_KEY, identityId != null? identityId : JSONObject.NULL );
        accountJson.put(CREATED_AT_KEY, localDateTimeUtil.toIso8601(account.getCreatedAt()));
        accountJson.put(GRANTS_KEY, grantMapSerializer.serialize(account.getGrants()));
        return accountJson;
    }

}
