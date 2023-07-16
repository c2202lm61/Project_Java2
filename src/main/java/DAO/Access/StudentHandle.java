package DAO.Access;

import Model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentHandle extends AbsSQLAccess<Student>{

    @Override
    public Boolean INSERT(Student item) {
        return null;
    }

    @Override
    public List<Student> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(Student item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
