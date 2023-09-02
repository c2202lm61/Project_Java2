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
    public Boolean INSERT(MClass item) throws SQLException {
        String sql;
        if(item.getID() == -1){
            sql = "INSERT INTO `class`(`grant_id`,`ID_MANAGER`) VALUES ('"+item.getGrandID()+"','"+item.getManagerID()+"')";
        } else {
            sql = "INSERT INTO `class`(`class_code`,`grant_id`,`ID_MANAGER`) VALUES ("+item.getID()+","+item.getGrandID()+",'"+item.getManagerID()+"')";

        }
            return JDBCDriver.SetQuery(sql);
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
        JDBCDriver.DestroyConnection();
        return a;
    }

    @Override
    public Boolean UPDATE(MClass item) {
        String  sql= "UPDATE `class` SET `class_code`="+item.getID()+",`grant_id`="+item.getGrandID()+",`ID_manager`="+item.getManagerID()+" WHERE `class_code`="+item.getID();
        System.out.println(sql);
        try {
            return JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean DELETE(int id) {
        try {
            JDBCDriver.SetQuery("CALL deleteFromClass("+id+")");
            return JDBCDriver.SetQuery("DELETE FROM `class` WHERE `class_code` = "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
