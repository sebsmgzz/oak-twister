package com.oaktwister.services.repos;

import com.oaktwister.models.aggregators.Platform;
import com.oaktwister.services.config.Context;
import com.oaktwister.services.json.PlatformSerializer;
import com.oaktwister.services.logging.Logger;

public class PlatformsRepo extends JsonRepo<Platform> {

    public static final String LOCATION = "platforms";

    public PlatformsRepo(Context context, PlatformSerializer platformSerializer, Logger logger) {
        super(context, platformSerializer, logger);
    }

    @Override
    protected String getRepoLocation() {
        return LOCATION;
    }

}
