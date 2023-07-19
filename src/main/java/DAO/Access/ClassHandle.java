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
    public Boolean INSERT(MClass item) {


        return null;
    }

    @Override
    public List<MClass> SELECT(String sql) throws SQLException {
        List<MClass> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            MClass b = new MClass();
            b.setID(resultSet.getInt("class_code"));
            b.setGrandID(resultSet.getInt("grand_id"));
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
        return null;
    }
}
