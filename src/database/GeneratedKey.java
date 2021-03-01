package database;

import java.sql.Statement;

public final class GeneratedKey {

    public static final GeneratedKey RETURN_GENERATED_KEYS = new GeneratedKey(Statement.RETURN_GENERATED_KEYS);
    public static final GeneratedKey NO_GENERATED_KEYS = new GeneratedKey(Statement.NO_GENERATED_KEYS);
    public static final GeneratedKey CLOSE_CURRENT_RESULT = new GeneratedKey(Statement.CLOSE_CURRENT_RESULT);
    public static final GeneratedKey KEEP_CURRENT_RESULT = new GeneratedKey(Statement.KEEP_CURRENT_RESULT);
    public static final GeneratedKey CLOSE_ALL_RESULTS = new GeneratedKey(Statement.CLOSE_ALL_RESULTS);
    public static final GeneratedKey EXECUTE_FAILED = new GeneratedKey(Statement.EXECUTE_FAILED);
    public static final GeneratedKey SUCCESS_NO_INFO = new GeneratedKey(Statement.SUCCESS_NO_INFO);

    private int value;

    public int getValue() {
        return value;
    }

    private GeneratedKey(int value) {
        this.value = value;
    }

}
