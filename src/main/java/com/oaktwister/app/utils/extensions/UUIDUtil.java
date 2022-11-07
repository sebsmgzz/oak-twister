package com.oaktwister.app.utils.extensions;

import java.util.UUID;

public class UUIDUtil {

    public final static String EMPTY_UUID = "00000000-0000-0000-0000-000000000000";

    public static UUID empty() {
        return UUID.fromString(EMPTY_UUID);
    }

}
