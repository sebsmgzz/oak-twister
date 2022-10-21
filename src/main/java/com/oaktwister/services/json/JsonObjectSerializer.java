package com.oaktwister.services.json;

import com.oaktwister.exceptions.UnknownGrantTypeException;
import org.json.JSONObject;

public interface JsonObjectSerializer<T> {

    T deserialize(JSONObject json) throws UnknownGrantTypeException;

    JSONObject serialize(T entity) throws UnknownGrantTypeException;

}
