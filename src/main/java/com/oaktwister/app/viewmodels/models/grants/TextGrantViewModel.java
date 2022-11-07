package com.oaktwister.app.viewmodels.models.grants;

import com.oaktwister.domain.models.grants.TextGrant;

public class TextGrantViewModel extends GrantViewModel<TextGrant, String> {

    @Override
    public TextGrant getGrant() {
        String name = getName();
        String value = getValue();
        return new TextGrant(name, value);
    }

}
