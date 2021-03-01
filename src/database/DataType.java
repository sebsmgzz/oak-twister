package database;

import java.sql.Types;

public final class DataType {

    public static final DataType NULL = new DataType(Types.NULL);
    public static final DataType INTEGER = new DataType(Types.INTEGER);
    public static final DataType REAL = new DataType(Types.FLOAT);
    public static final DataType TEXT = new DataType(Types.NVARCHAR);
    public static final DataType BLOB = new DataType(Types.BLOB);

    private final int value;

    public int getValue() {
        return value;
    }

    private DataType(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        switch (value) {
            case Types.NULL:
                return "NULL";
            case Types.INTEGER:
                return "INTEGER";
            case Types.REAL:
                return "FLOAT";
            case Types.NVARCHAR:
                return "TEXT";
            case Types.BLOB:
                return "BLOB";
            default:
                return null;
        }
    }

}
