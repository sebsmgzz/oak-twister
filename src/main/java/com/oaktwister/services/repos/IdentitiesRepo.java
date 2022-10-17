package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.services.Context;
import com.oaktwister.services.json.IdentityJsonObjectSerializer;
import com.oaktwister.services.logging.Logger;

public class IdentitiesRepo extends JsonRepo<Identity> {

    public static final String LOCATION = "identities";

    public IdentitiesRepo(Context context, IdentityJsonObjectSerializer identitySerializer, Logger logger) {
        super(context, identitySerializer, logger);
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}
