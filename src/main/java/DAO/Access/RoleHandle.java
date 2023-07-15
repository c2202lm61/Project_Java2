package DAO.Access;

import Model.Role;

import java.util.List;

public class RoleHandle extends AbsSQLAccess<Role>{
    protected RoleHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Role item) {

    }

    @Override
    public List<Role> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Role item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
