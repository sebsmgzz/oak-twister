package management;

import models.Platform;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Manager {

    private Connection connection;

    private Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            String path = System.getProperty("user.dir") + "/data/db.sqlite";
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        }
        return connection;
    }

    public Manager() {
        try(Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.addBatch(Queries.CREATE_PLATFORMS);
            statement.addBatch(Queries.CREATE_ACCOUNTS);
            statement.addBatch(Queries.CREATE_METAS);
            statement.addBatch(Queries.CREATE_FIELDS);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Platform> selectAllPlatforms() {
        List<Platform> platforms = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(Queries.SELECT_PLATFORMS);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                Platform platform = new Platform(id);
                platform.setName(resultSet.getString("name"));
                platform.setImage(resultSet.getBytes("image"));
                platforms.add(platform);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platforms;
    }

}
