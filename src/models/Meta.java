package models;

public class Meta extends Model {

    private final int id;
    private final Platform platform;
    private final String clazz;
    private String name;
    private String description;

    public int id() {
        return id;
    }

    public Platform platform() {
        return platform;
    }

    public String clazz() {
        return clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Meta(int id, Platform platform, String clazz) {
        this.id = id;
        this.platform = platform;
        this.clazz = clazz;
    }

    public Meta(Platform platform, String clazz) {
        this(-1, platform, clazz);
    }

    @Override
    public String toString() {
        return null;
    }

}
