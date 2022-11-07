package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.FlagGrant;

public class FlagGrantViewModel extends GrantViewModel<FlagGrant, Boolean> {

    @Override
    public FlagGrant getGrant() {
        String name = getName();
        Boolean value = getValue();
        return new FlagGrant(name, value);
    }

}
