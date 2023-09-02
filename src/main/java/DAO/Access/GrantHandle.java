package DAO.Access;

import DAO.JDBCDriver;
import Model.Block;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.JDBCDriver.conn;

public class GrantHandle extends AbsSQLAccess<Block>{

    @Override
    public Boolean INSERT(Block item) throws SQLException {
        String sql;
        if(item.getID() == -1){
            sql = "INSERT INTO `grants`(`name`) VALUES ('"+item.getName()+"')";
        }else {
            sql = "INSERT INTO `grants`(`id`,`name`) VALUES ("+item.getID()+",'"+item.getName()+"')";
        }
            return JDBCDriver.SetQuery(sql);
    }


    @Override
    public List<Block> SELECT(String sql) throws SQLException {
        List<Block> a = new ArrayList<>();

        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Block b = new Block();
            b.setID(resultSet.getInt("ID"));
            b.setName(resultSet.getString("name"));
            a.add(b);
        }
        JDBCDriver.DestroyConnection();
        return a;
    }

    @Override
    public Boolean UPDATE(Block item) throws SQLException {
        String  sql= "UPDATE `grants` SET `id`="+item.getID()+", `name`='"+item.getName()+"' WHERE id="+item.getID();
        System.out.println(sql);
        return JDBCDriver.SetQuery(sql);
    }

    @Override
    public Boolean DELETE(int id) {
        Boolean result = false;
        try {
            JDBCDriver.SetQuery("CALL deleteFromGrant("+id+")");
            return JDBCDriver.SetQuery("DELETE FROM `grants` WHERE `id` = "+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
