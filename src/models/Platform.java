package models;

public class Platform extends Model {

    private final int id;
    private String name;
    private byte[] image;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Platform(int id) {
        this.id = id;
    }

    public Platform() {
        this(-1);
    }

    @Override
    public String toString() {
        return name;
    }

}
