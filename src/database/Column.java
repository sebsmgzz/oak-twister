package database;

public class Column {

    private String name;
    private boolean isPrimaryKey;
    private Type type;
    private boolean notNull;

    public String getName() {
        return name;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public Type getType() {
        return type;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public Column(String name, Type type, boolean notNull, boolean isPrimaryKey) {
        this.name = name;
        this.type = type;
        this.notNull = notNull;
        this.isPrimaryKey = isPrimaryKey;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ");
        builder.append(type.toString()).append(" ");
        builder.append(isPrimaryKey? "PRIMARY KEY " : "");
        builder.append(notNull ? "" : "NOT NULL ");
        return builder.toString();
    }

}
