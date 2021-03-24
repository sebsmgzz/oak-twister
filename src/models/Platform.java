package models;

import database.representations.QueryResult;

import java.awt.Image;
import java.sql.SQLException;

public class Platform extends Model {

    private int id;
    private String name;
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
    public void serialize(QueryResult result) {
        try {
            id = result.getInt("id");
            name = result.getString("name");
            image = result.getImage("image");
        } catch (SQLException ignored) { }
    }

    @Override
    public String toString() {
        return name;
    }

}
