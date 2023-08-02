package DAO.Access;

import java.sql.SQLException;
import java.util.List;

public abstract class AbsSQLAccess<T> {
    protected String table;
    protected AbsSQLAccess() {

    }

    public abstract Boolean INSERT(T item);
    public abstract List<T> SELECT(String sql) throws SQLException;
    public abstract Boolean UPDATE(T item);
    public abstract Boolean DELETE(int id) throws SQLException;
}