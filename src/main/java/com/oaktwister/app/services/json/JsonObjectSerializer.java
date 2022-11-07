package com.oaktwister.app.services.json;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import org.json.JSONObject;

public interface JsonObjectSerializer<T> {

    T deserialize(JSONObject json) throws UnknownGrantTypeException;

    JSONObject serialize(T entity) throws UnknownGrantTypeException;

}
