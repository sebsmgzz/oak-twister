package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Identity;
import com.oaktwister.services.Context;
import com.oaktwister.services.json.IdentitySerializer;

public class IdentitiesRepo extends JsonRepo<Identity> {

    public static final String LOCATION = "identities";

    public IdentitiesRepo(Context context) {
        super(context, new IdentitySerializer());
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}
