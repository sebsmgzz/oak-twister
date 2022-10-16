package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.Context;
import com.oaktwister.services.json.PlatformSerializer;

public class PlatformsRepo extends JsonRepo<Platform> {
    public static final String LOCATION = "platforms";

    public PlatformsRepo(Context context) {
        super(context, new PlatformSerializer());
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}
