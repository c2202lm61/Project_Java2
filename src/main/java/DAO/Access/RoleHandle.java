package DAO.Access;

import Model.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleHandle extends AbsSQLAccess<Role>{

    @Override
    public Boolean INSERT(Role item) {
        return null;
    }

    @Override
    public List<Role> SELECT(String sql) throws SQLException {
        return null;
    }

    @Override
    public Boolean UPDATE(Role item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
