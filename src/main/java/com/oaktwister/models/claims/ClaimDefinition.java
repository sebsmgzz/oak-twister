package com.oaktwister.models.claims;

public class ClaimDefinition {

    private String name;
    private Class<?> type;
    private boolean isOptional;

    public ClaimDefinition(String name, Class<?> type, boolean isOptional) {
        this.name = name;
        this.type = type;
        this.isOptional = isOptional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

}
