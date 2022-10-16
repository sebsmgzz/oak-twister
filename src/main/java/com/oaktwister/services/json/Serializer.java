package com.oaktwister.services.json;

import org.json.JSONObject;

public interface Serializer<T> {

    T deserialize(JSONObject json);

    JSONObject serialize(T entity);

}
