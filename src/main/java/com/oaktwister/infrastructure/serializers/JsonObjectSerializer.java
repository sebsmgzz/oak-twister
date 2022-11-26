package com.oaktwister.infrastructure.serializers;

import org.json.JSONObject;

public interface JsonObjectSerializer<T> {

    T deserialize(JSONObject json);

    JSONObject serialize(T entity);

}
