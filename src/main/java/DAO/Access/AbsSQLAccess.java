package DAO.Access;

import java.sql.SQLException;
import java.util.List;

public abstract class AbsSQLAccess<T> {
    protected String table;
    protected AbsSQLAccess() {

    }

    public abstract void INSERT(T item);
    public abstract List<T> SELECT(String sql) throws SQLException;
    public abstract void UPDATE(T item);
    public abstract void DELETE(int id);
}