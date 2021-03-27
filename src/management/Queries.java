package management;

public final class Queries {

    public static final String CREATE_PLATFORMS = "CREATE TABLE IF NOT EXISTS platforms (" +
            "id INTEGER NOT NULL," +
            "name TEXT NOT NULL," +
            "image BLOB NOT NULL," +
            "PRIMARY KEY (id)," +
            "UNIQUE (name) );";

    public static final String INSERT_PLATFORMS = "INSERT INTO platforms (name, image) VALUES (?, ?);";

    public static final String UPDATE_PLATFORMS = "UPDATE platforms SET name = ?, image = ? WHERE id = ?;";

    public static final String DELETE_PLATFORMS = "DELETE FROM platforms WHERE id = ?;";

    public static final String SELECT_PLATFORMS = "SELECT * FROM platforms;";

    public static final String SELECT_PLATFORMS_BY_ID = "SELECT * FROM platforms WHERE id = ?;";

    public static final String CREATE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS accounts (" +
            "id INTEGER NOT NULL," +
            "name TEXT NOT NULL," +
            "platform INTEGER NOT NULL," +
            "PRIMARY KEY (id)," +
            "FOREIGN KEY (platform) REFERENCES platforms (id) );";

    public static final String INSERT_ACCOUNTS = "INSERT INTO accounts (name, platform) VALUES (?, ?);";

    public static final String UPDATE_ACCOUNTS = "UPDATE accounts SET name = ?, platform = ? WHERE id = ?;";

    public static final String DELETE_ACCOUNTS = "DELETE FROM accounts WHERE id = ?;";

    public static final String SELECT_ACCOUNTS = "SELECT * FROM accounts;";

    public static final String SELECT_ACCOUNTS_BY_PLATFORM = "SELECT * FROM accounts WHERE platform = ?;";

    public static final String CREATE_METAS = "CREATE TABLE IF NOT EXISTS metas (" +
            "id INTEGER," +
            "platform INTEGER," +
            "type INTEGER," +
            "name TEXT," +
            "description TEXT," +
            "PRIMARY KEY (id)," +
            "FOREIGN KEY (platform) REFERENCES platforms (id) );";

    public static final String INSERT_METAS = "INSERT INTO metas (platform, type, name, description) VALUES (?, ?, ?, ?);";

    public static final String UPDATE_METAS = "UPDATE metas SET platform = ?, type = ?, name = ?, description = ? WHERE id = ?;";

    public static final String DELETE_METAS = "DELETE FROM metas WHERE id = ?;";

    public static final String SELECT_METAS = "SELECT * FROM metas;";

    public static final String SELECT_METAS_BY_PLATFORM = "SELECT * FROM metas WHERE platform = ?;";

    public static final String CREATE_FIELDS = "CREATE TABLE IF NOT EXISTS fields (" +
            "account INTEGER," +
            "meta INTEGER," +
            "created TEXT," +
            "value BLOB," +
            "PRIMARY KEY (account, meta)," +
            "FOREIGN KEY (account) REFERENCES accounts (id)," +
            "FOREIGN KEY (meta) REFERENCES metas (id) );";

    public static final String INSERT_FIELDS = "INSERT INTO fields (account, meta, created, value) VALUES (?, ?, ?, ?);";

    public static final String UPDATE_FIELDS = "UPDATE fields SET value = ? WHERE account = ? AND meta = ?;";

    public static final String DELETE_FIELDS = "DELETE FROM fields WHERE account = ? AND meta = ?;";

    public static final String SELECT_FIELDS = "SELECT * FROM fields;";

    public static final String SELECT_FIELDS_BY_ACCOUNT = "SELECT * FROM fields WHERE account = ?;";

}
