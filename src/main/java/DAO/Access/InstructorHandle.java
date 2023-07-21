package DAO.Access;

import DAO.JDBCDriver;
import DAO.MySQLSupport;
import Model.Block;
import Model.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorHandle  extends AbsSQLAccess<Instructor>{


    @Override
    public Boolean INSERT(Instructor item) {
        Boolean result = false;
        String sql = "INSERT INTO `instructor`(`name`,`birthday`,`Gender`,`password`,`Email`,`Phone`) VALUES ('"+item.getName()+"','"+item.getBirthday()+"','"+item.getGender()+"','"+item.getPassword()+"','"+item.getEmail()+"','"+item.getPhone()+"')";
        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("thêm dữ liệu thành công "+a);
            result = true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Instructor> SELECT(String sql) throws SQLException {

        List<Instructor> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Instructor b = new Instructor();
            b.setID_NUMBER(resultSet.getInt("ID_NUMBER"));
            b.setName(resultSet.getString("name"));
            b.setBirthday(resultSet.getDate("birthday"));
            b.setGender(resultSet.getBoolean("gender"));
            b.setPassword(resultSet.getString("passwrod"));
            b.setEmail(resultSet.getString("Email"));
            b.setPhone(resultSet.getString("Phone"));
            a.add(b);
            }
        return a;

    }

    @Override
    public Boolean UPDATE(Instructor item) {
        Boolean result = false;
        String  sql= "UPDATE `instructor` SET `ID_NUMBER`='"+item.getID_NUMBER() +"',`name`='"+item.getName() +"',`birthday`='"+item.getBirthday() +"',`Gender`='"+item.getGender() +"',`password`='"+item.getPassword() +"',`Email`='"+item.getEmail() +"',`Phone`='"+item.getPhone() +"' WHERE id="+item.getID_NUMBER();

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
            boolean a =JDBCDriver.SetQuery(" DELETE FROM `instructor` WHERE `ID_NUMBER` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
