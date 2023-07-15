package DAO.Access;

import Model.SubjectStudent;

import java.util.List;

public class SubjectStudentHandle extends AbsSQLAccess<SubjectStudent> {
    protected SubjectStudentHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(SubjectStudent item) {

    }

    @Override
    public List<SubjectStudent> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(SubjectStudent item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
