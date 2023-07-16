package DAO.Access;

import Model.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectHandle extends AbsSQLAccess<Subject> {

    @Override
    public Boolean INSERT(Subject item) {
        return null;
    }

    @Override
    public List<Subject> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(Subject item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
