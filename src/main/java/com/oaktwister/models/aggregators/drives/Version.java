package com.oaktwister.models.aggregators.drives;

public class Version {
    private final static String VERSION_REGEX = "(?<major>[^.\\n]+)\\.(?<minor>[^.\\n]+)\\.(?<patch>[^.\\n]+)";

    private final String version;

    public Version(String version) {
        this.version = version;
    }

    public int major() {
        return Integer.parseInt(version.split(VERSION_REGEX)[0]);
    }

    public int minor() {
        return Integer.parseInt(version.split(VERSION_REGEX)[1]);
    }

    public int patch() {
        return Integer.parseInt(version.split(VERSION_REGEX)[2]);
    }

}