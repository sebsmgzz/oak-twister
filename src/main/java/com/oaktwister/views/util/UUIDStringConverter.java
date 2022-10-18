package com.oaktwister.views.util;

import javafx.util.StringConverter;

import java.util.UUID;

public class UUIDStringConverter extends StringConverter<UUID> {

    private final static String EMPTY_UUID = "00000000-0000-0000-0000-000000000000";

    @Override
    public String toString(UUID uuid) {
        if(uuid == null) {
            return null;
        }
        return uuid.toString();
    }

    @Override
    public UUID fromString(String string) {
        if(string == null) {
            string = EMPTY_UUID;
        }
        return UUID.fromString(string);
    }

}
