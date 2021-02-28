package database.entities;

import java.sql.Types;

public class DataType {

    public static final int NULL = Types.NULL;
    public static final int INTEGER = Types.INTEGER;
    public static final int REAL = Types.FLOAT;
    public static final int TEXT = Types.NVARCHAR;
    public static final int BLOB = Types.BLOB;

    private DataType() { }

    public static String toString(int type) {
        switch (type) {
            case NULL:
                return "NULL";
            case INTEGER:
                return "INTEGER";
            case REAL:
                return "FLOAT";
            case TEXT:
                return "TEXT";
            case BLOB:
                return "BLOB";
            default:
                return null;
        }
    }

}
