package models;

import java.util.Date;

public abstract class Field extends Model {

    private final Account account;
    private final Meta meta;
    private final Date created;

    public Account account() {
        return account;
    }

    public Meta meta() {
        return meta;
    }

    public Date created() {
        return created;
    }

    public abstract Object getBlob();

    public abstract boolean setBlob(Object blob);

    public Field(Account account, Meta meta, Date created) {
        this.account = account;
        this.meta = meta;
        this.created = created;
    }

    public Field(Account account, Meta meta) {
        this(account, meta, new Date());
    }

    @Override
    public abstract String toString();

}
