package DAO.Access;

import DAO.JDBCDriver;
import Model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentHandle extends AbsSQLAccess<Student>{

    @Override
    public Boolean INSERT(Student item) {
        Boolean result = false;
        String sql = "INSERT INTO `student`(`Social_Securty_Number`, `Current_address`, `Phone`, `Birthday`, `Gender`, `Class_code`, `Name`)" + " VALUES ('"+item.getSocialSecurtyNumber()+"','"+item.getAddress()+"','"+item.getPhone()+"','"+item.getBirthday()+"','"+item.getGender()+"',"+item.getClassID()+",'"+item.getName()+"')";
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
    public List<Student> SELECT(String sql) throws SQLException {
        List<Student> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Student b = new Student();
            b.setID(resultSet.getInt("Student_id"));
            b.setName(resultSet.getString("Name"));
            b.setSocialSecurtyNumber(resultSet.getString("Social_Securty_Number"));
            b.setBirthday(resultSet.getDate("Birthday"));
            b.setGender(resultSet.getBoolean("Gender"));
            b.setAddress(resultSet.getString("Current_address"));
            b.setAddress(resultSet.getString("Phone"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Student item) {
        Boolean result = false;
        String  sql= "UPDATE `student` SET `Student_id`="+item.getID()+",`Social_Securty_Number`='"+item.getSocialSecurtyNumber()+"',`Current_address`='"+item.getAddress()+"',`Phone`='"+item.getPhone()+"',`Birthday`='"+item.getBirthday()+"',`Gender`='"+item.getGender()+"',`Class_code`="+item.getClassID()+",`Name`='"+item.getName()+"' WHERE id="+item.getID();
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
            boolean a =JDBCDriver.SetQuery("DELETE FROM `student` WHERE `Student_id` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
