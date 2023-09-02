package DAO.Access;

import DAO.JDBCDriver;
import Model.TypeScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeScoreHandle extends AbsSQLAccess<TypeScore> {
    @Override
    public Boolean INSERT(TypeScore item) {
        String sql;
        if(item.getID() == -1){sql = "INSERT INTO `type_score`(`name`) VALUES ('"+item.getName()+"')";}
        else {sql = "INSERT INTO `type_score`(`ts_id`,`name`) VALUES ("+item.getID()+",'"+item.getName()+"')";}
        System.out.println(sql);
        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("them du lieu thành công:"+a);
           return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TypeScore> SELECT(String sql) throws SQLException {
       List<TypeScore> a = new ArrayList<>();
       final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
       while (resultSet.next()){
           TypeScore b = new TypeScore();
           b.setID(resultSet.getInt("ts_id"));
           b.setName(resultSet.getString("name"));
           a.add(b);
       }
       JDBCDriver.DestroyConnection();
       return a;
    }

    @Override
    public Boolean UPDATE(TypeScore item) {
        String  sql= "UPDATE `type_score` SET `ts_id`="+item.getID()+",`name`='"+item.getName()+"' WHERE `ts_id`="+item.getID();
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
           return JDBCDriver.SetQuery("DELETE FROM `type_score` WHERE `ts_id` = "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
