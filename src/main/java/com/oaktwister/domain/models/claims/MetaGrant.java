package com.oaktwister.domain.models.claims;

import com.oaktwister.domain.models.grants.DateTimeGrant;
import com.oaktwister.domain.models.grants.FlagGrant;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.NumberGrant;
import com.oaktwister.domain.models.grants.SecretGrant;
import com.oaktwister.domain.models.grants.TextGrant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MetaGrant {

    public final static String GRANT_PREFIX = "Grant";

    private final Class<? extends Grant<?>> grantClass;

    public MetaGrant(Class<? extends Grant<?>> grantClass) {
        this.grantClass = grantClass;
    }

    public Class<? extends Grant<?>> getGrantClass() {
        return grantClass;
    }

    public String getName() {
        String className = grantClass.getSimpleName();
        return className.replace(GRANT_PREFIX, "");
    }

    public static MetaGrant tryParse(String name) {
        Collection<MetaGrant> metaGrants = getAll();
        for(MetaGrant metaGrant : metaGrants) {
            if(Objects.equals(metaGrant.getName(), name)) {
                return metaGrant;
            }
        }
        return null;
    }

    public static Collection<MetaGrant> getAll() {
        ArrayList<MetaGrant> grants = new ArrayList<>();
        grants.add(new MetaGrant(DateTimeGrant.class));
        grants.add(new MetaGrant(FlagGrant.class));
        grants.add(new MetaGrant(NumberGrant.class));
        grants.add(new MetaGrant(SecretGrant.class));
        grants.add(new MetaGrant(TextGrant.class));
        return grants;
    }

}
