package com.oaktwister.models.drives;

public class Drive {

    private final String path;
    private final boolean hasMeta;

    public Drive(String path, boolean hasMeta) {
        this.path = path;
        this.hasMeta = hasMeta;
    }

    public String getPath() {
        return path;
    }

    public boolean isHasMeta() {
        return hasMeta;
    }

}
