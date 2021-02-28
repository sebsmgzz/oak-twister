package models.platform;

import middleware.annotations.Column;
import middleware.annotations.Table;
import database.entities.DataType;
import models.Model;

import java.awt.Image;

@Table(name = "platforms")
public class Platform extends Model {

    @Column(name = "id", type = DataType.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "name", type = DataType.TEXT)
    private String name;

    @Column(name = "image", type = DataType.BLOB)
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
