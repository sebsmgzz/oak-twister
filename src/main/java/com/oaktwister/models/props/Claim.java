package com.oaktwister.models.props;

public class Claim {

    private String name;
    private Class<? extends Grant<?>> grantType;
    private boolean isOptional;

    public Claim(String name, Class<? extends Grant<?>> grantType, boolean isOptional) {
        this.name = name;
        this.grantType = grantType;
        this.isOptional = isOptional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getGrantType() {
        return grantType;
    }

    public void setGrantType(Class<? extends Grant<?>> grantType) {
        this.grantType = grantType;
    }

    public boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

}
