package DAO.Access;

import Model.InstructorRole;

import java.sql.SQLException;
import java.util.List;

public class InstructorRoleHandle  extends AbsSQLAccess<InstructorRole> {
    @Override
    public Boolean INSERT(InstructorRole item) {
        return null;
    }

    @Override
    public List<InstructorRole> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(InstructorRole item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
