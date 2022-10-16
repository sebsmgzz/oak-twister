package com.oaktwister.models.claims;

import java.time.LocalDateTime;

public class DateTimeClaim extends Claim<LocalDateTime> {

    public DateTimeClaim(String name, LocalDateTime value) {
        super(name, value);
    }

    public DateTimeClaim(String name) {
        super(name);
    }

}
