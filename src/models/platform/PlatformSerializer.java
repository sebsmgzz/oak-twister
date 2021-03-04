package models.platform;

import database.representations.QueryResult;
import models.Serializer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;

public class PlatformSerializer extends Serializer<Platform> {

    @Override
    public Platform serialize(QueryResult result) {
        Platform platform = new Platform();
        try {
            platform.setId(result.getInt("id"));
            platform.setName(result.getString("name"));
            platform.setImage(result.getImage("image"));
        } catch (SQLException e) {
            return platform;
        }
        return platform;
    }

    @Override
    public Platform serialize(HashMap<String, String> map) {
        Platform platform = new Platform();
        try {
            platform.setId(Integer.parseInt(map.getOrDefault("id", null)));
            platform.setName(map.getOrDefault("name", null));
            platform.setImage(string2Image(map.getOrDefault("image", null)));
        } catch (Exception e) {
            return platform;
        }
        return platform;
    }

    private BufferedImage string2Image(String base64String) throws IOException {
        String[] strings = base64String.split(",");
        byte[] data = Base64.getDecoder().decode(strings[1]);
        return ImageIO.read(new ByteArrayInputStream(data));
    }

}
