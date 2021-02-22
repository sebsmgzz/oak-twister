package database;

public class Column {

    private String name;
    private boolean isPrimaryKey;
    private Type type;
    private boolean allowNull;

    public String getName() {
        return name;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public Type getType() {
        return type;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public Column(String name, Type type, boolean allowNull, boolean isPrimaryKey) {
        this.name = name;
        this.type = type;
        this.allowNull = allowNull;
        this.isPrimaryKey = isPrimaryKey;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ");
        builder.append(type.toString()).append(" ");
        builder.append(isPrimaryKey? "PRIMARY KEY " : "");
        builder.append(allowNull? "" : "NOT NULL ");
        return builder.toString();
    }

}
