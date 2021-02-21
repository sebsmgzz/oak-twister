package database;

import java.util.ArrayList;
import java.util.List;

public class Column {

    private String name;
    private boolean isPrimaryKey;
    private Type type;
    private int size;
    private boolean allowNull;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    @Override
    public String toString() {
        List<String> definition = new ArrayList<>();
        definition.add(name);
        String strType = type.toString();
        if (type == Type.TEXT) {
            strType += "(" + size + ")";
        }
        definition.add(strType);
        if(isPrimaryKey) {
            definition.add("PRIMARY KEY");
        }
        if(!allowNull) {
            definition.add("NOT NULL");
        }
        return String.join(" ", definition);
    }

}
