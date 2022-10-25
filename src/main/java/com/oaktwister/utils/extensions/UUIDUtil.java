package com.oaktwister.utils.extensions;

import java.util.UUID;

public class UUIDUtil {

    public final static String EMPTY_UUID = "00000000-0000-0000-0000-000000000000";

    public UUID empty() {
        return UUID.fromString(EMPTY_UUID);
    }

}
