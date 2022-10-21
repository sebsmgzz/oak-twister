package com.oaktwister.models.grants;

import java.time.LocalDateTime;

public class DateTimeGrant extends Grant<LocalDateTime> {

    public DateTimeGrant(String name, LocalDateTime value) {
        super(name, value);
    }

    public DateTimeGrant(String name) {
        super(name);
    }

}
