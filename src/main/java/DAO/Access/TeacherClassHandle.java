package DAO.Access;

import DAO.JDBCDriver;
import Model.Subject;
import Model.Teacher;
import Model.TeacherClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherClassHandle extends AbsSQLAccess<TeacherClass> {


    @Override
    public Boolean INSERT(TeacherClass item) {
        return null;
    }

    @Override
    public List<TeacherClass> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(TeacherClass item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
