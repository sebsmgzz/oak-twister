package com.oaktwister.domain.models.claims;

import com.oaktwister.domain.exceptions.UnknownMetaGrantException;
import com.oaktwister.domain.models.grants.DateTimeGrant;
import com.oaktwister.domain.models.grants.FlagGrant;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.NumberGrant;
import com.oaktwister.domain.models.grants.SecretGrant;
import com.oaktwister.domain.models.grants.TextGrant;
import com.oaktwister.domain.seedwork.Enumerable;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MetaGrant extends Enumerable<Integer, Class<? extends Grant<?>>> {

    public final static String GRANT_PREFIX = "Grant";

    public final static MetaGrant DATE_TIME_META = new MetaGrant(0, DateTimeGrant.class);
    public final static MetaGrant FLAG_GRANT_META = new MetaGrant(1, FlagGrant.class);
    public final static MetaGrant NUMBER_GRANT_META = new MetaGrant(2, NumberGrant.class);
    public final static MetaGrant SECRET_GRANT_META = new MetaGrant(3, SecretGrant.class);
    public final static MetaGrant TEXT_GRANT_META = new MetaGrant(4, TextGrant.class);

    private MetaGrant(@NotNull Integer key, @NotNull Class<? extends Grant<?>> value) {
        super(key, value);
    }

    public String getName() {
        String className = getValue().getSimpleName();
        return className.replace(GRANT_PREFIX, "");
    }

    public static MetaGrant tryParse(String name) {
        Collection<MetaGrant> metaGrants = getAll();
        for(MetaGrant metaGrant : metaGrants) {
            String grantName = metaGrant.getName();
            if(Objects.equals(grantName, name)) {
                return metaGrant;
            }
        }
        return null;
    }

    public static MetaGrant parse(String name) throws UnknownMetaGrantException {
        MetaGrant metaGrant = tryParse(name);
        if(metaGrant == null) {
            throw new UnknownMetaGrantException(name);
        } else {
            return metaGrant;
        }
    }

    public static Collection<MetaGrant> getAll() {
        ArrayList<MetaGrant> grants = new ArrayList<>();
        grants.add(DATE_TIME_META);
        grants.add(FLAG_GRANT_META);
        grants.add(NUMBER_GRANT_META);
        grants.add(SECRET_GRANT_META);
        grants.add(TEXT_GRANT_META);
        return grants;
    }

}
