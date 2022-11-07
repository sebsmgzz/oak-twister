package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.DateTimeGrant;
import com.oaktwister.domain.models.grants.Grant;

import java.time.LocalDateTime;

public class DateTimeGrantViewModel extends GrantViewModel<LocalDateTime> {

    @Override
    public Grant<LocalDateTime> getGrant() {
        String name = getName();
        LocalDateTime value = getValue();
        return new DateTimeGrant(name, value);
    }

    @Override
    public void setGrant(Grant<LocalDateTime> grant) {
        setName(grant.getName());
        setValue(grant.getValue());
    }

}
