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
    public GrantHandle(){
        table = "grants";
    }
    @Override
    public Boolean INSERT(Block item)
    {
        Boolean result = false;
        String sql = "INSERT INTO `grants`(`name`) VALUES ('"+item.getName()+"')";
        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("them du lieu thành công:"+a);
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
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
        return a;
    }

    @Override
    public Boolean UPDATE(Block item) {
        Boolean result = false;
        String  sql= "UPDATE `grants` SET `id`="+item.getID()+" `name`='"+item.getName()+"' WHERE id="+item.getID();
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
            boolean a =JDBCDriver.SetQuery("DELETE FROM `grants` WHERE `id` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
