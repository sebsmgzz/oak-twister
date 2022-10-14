package com.oaktwister.views.util;

import javafx.util.StringConverter;

import java.util.UUID;

public class UUIDStringConverter extends StringConverter<UUID> {

    @Override
    public String toString(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID fromString(String string) {
        return UUID.fromString(string);
    }

}
