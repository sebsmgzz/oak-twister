package com.oaktwister.domain.models.claims;

import com.oaktwister.domain.seedwork.ValueObject;
import java.util.ArrayList;
import java.util.List;

public class Claim extends ValueObject {

    private final String name;
    private final MetaGrant metaGrant;
    private final boolean isOptional;

    public Claim(String name, MetaGrant metaGrant, boolean isOptional) {
        this.name = name;
        this.metaGrant = metaGrant;
        this.isOptional = isOptional;
    }

    public String getName() {
        return name;
    }

    public MetaGrant getMetaGrant() {
        return metaGrant;
    }

    public boolean getIsOptional() {
        return isOptional;
    }

    @Override
    protected List<Object> getComponents() {
        ArrayList<Object> components = new ArrayList<>();
        components.add(name);
        components.add(metaGrant);
        components.add(isOptional);
        return components;
    }

}
