package DAO.Access;

import Model.Student;

import java.util.List;

public class StudentHandle extends AbsSQLAccess<Student>{
    protected StudentHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Student item) {

    }

    @Override
    public List<Student> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Student item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
