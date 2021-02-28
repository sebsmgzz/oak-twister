package models.identity;

import middleware.annotations.Column;
import middleware.annotations.Table;
import database.entities.DataType;
import models.Model;

@Table(name = "identities")
public class Identity extends Model {

    @Column(name = "id", type = DataType.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "first_name", type = DataType.TEXT)
    private String firstName;

    @Column(name = "last_name", type = DataType.TEXT)
    private String lastName;

    @Column(name = "email", type = DataType.TEXT)
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
    public String toString() {
        return email;
    }

}
