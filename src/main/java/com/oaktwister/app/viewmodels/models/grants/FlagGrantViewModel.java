package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.FlagGrant;
import com.oaktwister.domain.models.grants.Grant;

public class FlagGrantViewModel extends GrantViewModel<Boolean> {

    @Override
    public Grant<Boolean> getGrant() {
        String name = getName();
        Boolean value = getValue();
        return new FlagGrant(name, value);
    }

    @Override
    public void setGrant(Grant<Boolean> grant) {
        setName(grant.getName());
        setValue(grant.getValue());
    }

}
