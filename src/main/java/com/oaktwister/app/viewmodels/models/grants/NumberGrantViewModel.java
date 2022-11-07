package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.NumberGrant;

public class NumberGrantViewModel extends GrantViewModel<NumberGrant, Number> {

    @Override
    public NumberGrant getGrant() {
        String name = getName();
        Number value = getValue();
        return new NumberGrant(name, value);
    }

}
