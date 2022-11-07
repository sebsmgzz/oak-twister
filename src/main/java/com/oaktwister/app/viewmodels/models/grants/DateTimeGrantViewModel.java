package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.DateTimeGrant;

import java.time.LocalDateTime;

public class DateTimeGrantViewModel extends GrantViewModel<DateTimeGrant, LocalDateTime> {

    @Override
    public DateTimeGrant getGrant() {
        String name = getName();
        LocalDateTime value = getValue();
        return new DateTimeGrant(name, value);
    }

}
