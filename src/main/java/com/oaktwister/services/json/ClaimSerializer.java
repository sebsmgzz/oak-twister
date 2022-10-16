package com.oaktwister.services.json;

import com.oaktwister.models.claims.*;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Objects;

public class ClaimSerializer implements Serializer<Claim<?>> {

    @Override
    public Claim<?> deserialize(JSONObject json) {
        String name = json.getString("name");
        String type = json.getString("type");
        if(Objects.equals(type, DateTimeClaim.class.getTypeName())) {
            String value = json.getString("value");
            return new DateTimeClaim(name, LocalDateTime.parse(value));
        } else if (Objects.equals(type, FlagClaim.class.getTypeName())) {
            boolean value = json.getBoolean("value");
            return new FlagClaim(name, value);
        } else if (Objects.equals(type, NumberClaim.class.getTypeName())) {
            double value = json.getDouble("value");
            return new NumberClaim(name, value);
        } else if (Objects.equals(type, SecretClaim.class.getTypeName())) {
            JSONObject jsonValue = json.getJSONObject("value");
            String value = jsonValue.getString("value");
            String hint = jsonValue.getString("hint");
            return new SecretClaim(name, value, hint);
        } else if (Objects.equals(type, TextClaim.class.getTypeName())) {
            String value = json.getString("value");
            return new TextClaim(value);
        } else {
            return null;
        }
    }

    @Override
    public JSONObject serialize(Claim<?> entity) {
        Class<?> type = entity.getClass();
        JSONObject json = new JSONObject();
        json.put("name", entity.getName());
        json.put("type", type.getName());
        if(DateTimeClaim.class.equals(type)) {
            DateTimeClaim claim = (DateTimeClaim)entity;
            json.put("value", claim.getValue().toString());
        } else if (FlagClaim.class.equals(type)) {
            FlagClaim claim = (FlagClaim)entity;
            json.put("value", claim.getValue().booleanValue());
        } else if (NumberClaim.class.equals(type)) {
            NumberClaim claim = (NumberClaim)entity;
            json.put("value", claim.getValue().doubleValue());
        } else if (SecretClaim.class.equals(type)) {
            SecretClaim claim = (SecretClaim)entity;
            JSONObject claimJson = new JSONObject();
            claimJson.put("value", claim.getValue());
            claimJson.put("hint", claim.getHint());
            json.put("value", claimJson);
        } else if (TextClaim.class.equals(type)) {
            TextClaim claim = (TextClaim)entity;
            json.put("value", claim.getValue());
        }
        return json;
    }

}
