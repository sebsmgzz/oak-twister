package models;

import annotations.Column;
import annotations.Table;
import database.Type;

@Table(name = "identities")
public class Identity extends BaseModel {

    @Column(name = "id", type = Type.INTEGER, primaryKey = true)
    private int id;

    @Column(name = "first_name", type = Type.TEXT, size = 200)
    private String firstName;

    @Column(name = "last_name", type = Type.TEXT, size = 200)
    private String lastName;

    @Column(name = "email", type = Type.TEXT, size = 200)
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
