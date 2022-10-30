package com.oaktwister.models.drives;

public class Version {

    private final int major;
    private final int minor;
    private final int patch;

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public int major() {
        return major;
    }

    public int minor() {
        return minor;
    }

    public int patch() {
        return patch;
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d", major, minor, patch);
    }

    public static Version parse(String version) {
        String[] breadcrumbs = version.split("\\.");
        int major = Integer.parseInt(breadcrumbs[0]);
        int minor = Integer.parseInt(breadcrumbs[1]);
        int patch = Integer.parseInt(breadcrumbs[2]);
        return new Version(major, minor, patch);
    }

}
