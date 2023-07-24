package DAO.Access;

import DAO.JDBCDriver;
import DAO.MySQLSupport;
import Model.MClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassHandle extends AbsSQLAccess<MClass>{
    @Override
    // check lại hộ em nhe
    public Boolean INSERT(MClass item) {
        Boolean result = false;
        String sql = "INSERT INTO `class`(`grant_id`,`ID_MANAGER`) VALUES ("+item.getGrandID()+","+item.getManagerID()+")";
        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("thêm dữ .iệu thành công "+a);
            result = true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<MClass> SELECT(String sql) throws SQLException {
        List<MClass> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            MClass b = new MClass();
            b.setID(resultSet.getInt("class_code"));
            b.setGrandID(resultSet.getInt("grant_id"));
            b.setManagerID(resultSet.getInt("ID_manager"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(MClass item) {

        Boolean result = false;
        String  sql= "UPDATE `class` SET `class_code`='"+item.getID()+"',`grant_id`='"+item.getGrandID()+"',`ID_manager`='"+item.getManagerID()+"' WHERE id="+item.getID();
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
        Boolean result = false;
        try {
            boolean a =JDBCDriver.SetQuery("DELETE FROM `class` WHERE `class_code` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
