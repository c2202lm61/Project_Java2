package DAO.Access;

import Model.SubjectStudent;

import java.sql.SQLException;
import java.util.List;

public class SubjectStudentHandle extends AbsSQLAccess<SubjectStudent> {

    @Override
    public Boolean INSERT(SubjectStudent item) {
        return null;
    }

    @Override
    public List<SubjectStudent> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(SubjectStudent item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
