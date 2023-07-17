package DAO.Access;

import DAO.JDBCDriver;
import Model.Instructor;
import Model.InstructorRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorRoleHandle  extends AbsSQLAccess<InstructorRole> {
    @Override
    public Boolean INSERT(InstructorRole item) {
        return null;
    }

    @Override
    public List<InstructorRole> SELECT(String sql) throws SQLException {
        List<InstructorRole> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            InstructorRole b = new InstructorRole();
            b.setID_NUMBER(resultSet.getInt("ID_NUMBER"));
            b.setRole_id(resultSet.getInt("Role_id"));
            b.setTclass_id(resultSet.getInt("Tclass_id"));
            a.add(b);
        }
return a;

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
