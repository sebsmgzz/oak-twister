package models;

public class Account extends Model {

    private final int id;
    private String name;
    private final Platform platform;
    private final FieldList fields;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Platform platform() {
        return platform;
    }

    public FieldList fields() {
        return fields;
    }

    public Account(int id, Platform platform) {
        this.id = id;
        this.platform = platform;
        this.fields = new FieldList();
    }

    public Account(Platform platform) {
        this(-1, platform);
    }

    @Override
    public String toString() {
        return name;
    }

}
