package DAO.Access;

import DAO.JDBCDriver;
import Model.InstructorRole;
import Model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleHandle extends AbsSQLAccess<Role>{

    @Override
    public Boolean INSERT(Role item) {
        String sql = "INSERT INTO `role`(`role_name`) VALUES ('"+item.getName()+"')";
        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("them du lieu thành công:"+a);

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> SELECT(String sql) throws SQLException {
         List<Role> a = new ArrayList<>();
         final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
         while (resultSet.next()){
             Role b = new Role();
             b.setId(resultSet.getInt("id"));
             b.setName(resultSet.getString("name"));
             a.add(b);
         }
         return a;
    }

    @Override
    public Boolean UPDATE(Role item) {
        Boolean result = false;
        String  sql= "UPDATE `role` SET `id`='"+item.getId()+"',`role_name`='"+item.getName()+"' WHERE id="+item.getId();
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
