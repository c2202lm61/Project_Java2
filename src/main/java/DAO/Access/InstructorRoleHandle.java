package DAO.Access;

import Model.InstructorRole;

import java.util.List;

public class InstructorRoleHandle  extends AbsSQLAccess<InstructorRole> {
    protected InstructorRoleHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(InstructorRole item) {

    }

    @Override
    public List<InstructorRole> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(InstructorRole item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
