package DAO.Access;

import Model.Subject;

import java.util.List;

public class SubjectHandle extends AbsSQLAccess<Subject> {
    protected SubjectHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Subject item) {

    }

    @Override
    public List<Subject> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Subject item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
