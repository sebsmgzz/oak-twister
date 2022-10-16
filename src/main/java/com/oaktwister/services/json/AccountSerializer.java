package com.oaktwister.services.json;

import com.oaktwister.models.aggregators.Account;
import com.oaktwister.models.claims.Claim;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AccountSerializer implements Serializer<Account> {
    private final static String ID_KEY = "id";
    private final static String PLATFORM_ID_KEY = "platformId";
    private final static String IDENTITY_ID_KEY = "identityId";
    private final static String CREATED_AT_KEY = "createdAt";
    private final static String CLAIMS_KEY = "claims";
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String DATE_TIME_ZONE = "UTC";

    private final ClaimSerializer claimsStrategy = new ClaimSerializer();
    private final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(DATE_TIME_FORMAT)
            .withZone(ZoneId.of(DATE_TIME_ZONE));

    @Override
    public Account deserialize(JSONObject json) {
        Account account = new Account(
            UUID.fromString(json.get(ID_KEY).toString()),
            UUID.fromString(json.get(PLATFORM_ID_KEY).toString()),
            UUID.fromString(json.get(IDENTITY_ID_KEY).toString()),
            LocalDateTime.parse(json.getString(CREATED_AT_KEY), formatter));
        for (Object obj : json.getJSONArray(CLAIMS_KEY)) {
            JSONObject jsonClaim = new JSONObject(obj);
            Claim<?> claim = claimsStrategy.deserialize(jsonClaim);
            account.claims().add(claim);
        }
        return account;
    }

    @Override
    public JSONObject serialize(Account account) {
        JSONObject json = new JSONObject();
        json.put(ID_KEY, account.getId());
        json.put(PLATFORM_ID_KEY, account.getPlatformId());
        json.put(IDENTITY_ID_KEY, account.getIdentityId());
        json.put(CREATED_AT_KEY, account.getCreatedAt());
        JSONArray jClaims = new JSONArray();
        for(Claim<?> claim : account.claims()) {
            JSONObject jClaim = claimsStrategy.serialize(claim);
            jClaims.put(jClaim);
        }
        json.put(CLAIMS_KEY, jClaims);
        return json;
    }

}
