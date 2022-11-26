package com.oaktwister.infrastructure.serializers.grants;

import com.oaktwister.app.utils.extensions.LocalDateTimeUtil;
import com.oaktwister.domain.models.grants.DateTimeGrant;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class DateTimeGrantSerializer extends GrantSerializer<DateTimeGrant> {

    @Override
    public DateTimeGrant deserialize(JSONObject json) {
        String name = json.getString(NAME_KEY);
        LocalDateTime value = LocalDateTimeUtil.fromIso8601(json.getString(VALUE_KEY));
        return new DateTimeGrant(name, value);
    }

    @Override
    public JSONObject serialize(DateTimeGrant grant) {
        JSONObject json = new JSONObject();
        json.put(NAME_KEY, grant.getName());
        json.put(TYPE_KEY, DateTimeGrant.class.getTypeName());
        json.put(VALUE_KEY, LocalDateTimeUtil.toIso8601(grant.getValue()));
        return json;
    }

}
