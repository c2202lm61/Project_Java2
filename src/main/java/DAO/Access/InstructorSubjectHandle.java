package DAO.Access;

import Model.InstructorSubject;

import java.sql.SQLException;
import java.util.List;

public class InstructorSubjectHandle extends AbsSQLAccess<InstructorSubject> {

    @Override
    public Boolean INSERT(InstructorSubject item) {
        return null;
    }

    @Override
    public List<InstructorSubject> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(InstructorSubject item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
