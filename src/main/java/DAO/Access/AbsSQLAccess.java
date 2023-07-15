package DAO.Access;

import java.util.List;

public abstract class AbsSQLAccess<T> {
    protected String table;
    protected AbsSQLAccess(String table) {
        this.table = table;
    }

    public abstract void INSERT(T item);
    public abstract List<T> SELECT(String sql);
    public abstract void UPDATE(T item);
    public abstract void DELETE(int id);
}