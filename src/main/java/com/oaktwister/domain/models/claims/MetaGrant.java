package com.oaktwister.domain.models.claims;

import com.oaktwister.domain.models.grants.Grant;

public class MetaGrant {

    private final Class<? extends Grant<?>> grantClass;

    public MetaGrant(Class<? extends Grant<?>> grantClass) {
        this.grantClass = grantClass;
    }

    public Class<? extends Grant<?>> getGrantClass() {
        return grantClass;
    }

    public String getName() {
        return grantClass.getSimpleName();
    }

}
