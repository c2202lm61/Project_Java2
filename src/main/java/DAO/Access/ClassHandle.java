package DAO.Access;

import java.util.List;

public class ClassHandle extends AbsSQLAccess<Class>{
    protected ClassHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Class item) {

    }

    @Override
    public List<Class> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Class item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
