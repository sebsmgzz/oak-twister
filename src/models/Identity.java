package models;

import database.representations.QueryResult;
import models.Model;

import java.sql.SQLException;

public class Identity extends Model {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void serialize(QueryResult result) {
        try {
            id = result.getInt("id");
            email = result.getString("email");
            firstName = result.getString("firstName");
            lastName = result.getString("lastName");
        } catch (SQLException ignored) { }
    }

    @Override
    public String toString() {
        return email;
    }

}
