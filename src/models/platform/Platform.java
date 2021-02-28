package models.platform;

import annotations.Column;
import annotations.Table;
import database.DataType;
import models.BaseDataModel;

import java.awt.Image;

@Table(name = "platforms")
public class Platform extends BaseDataModel {

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
