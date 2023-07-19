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
        Boolean result = false;
        String  sql= "UPDATE `instructor_role` SET `tclass_id`='"+item.getTclass_id()+"',`Role_id`='"+item.getRole_id()+"',`ID_NUMBER`='"+item.getID_NUMBER()+"' WHERE id="+item.getTclass_id();
        System.out.println(sql);
        try {
            boolean a =JDBCDriver.SetQuery(sql);
            if (a)System.out.println("Cập nhật dữ liệu thành công");
            else System.out.println("Cập nhật dữ liệu không thành công");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
