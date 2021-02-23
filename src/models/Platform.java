package models;

import annotations.Column;
import annotations.Table;
import database.metaentities.Type;

import java.awt.Image;

@Table(name = "platforms")
public class Platform extends BaseModel {

    @Column(name = "id", type = Type.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "name", type = Type.TEXT)
    private String name;

    @Column(name = "image", type = Type.BLOB)
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

}
