package DAO.Access;

import DAO.JDBCDriver;
import Model.InstructorRole;
import Model.InstructorSubject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorSubjectHandle extends AbsSQLAccess<InstructorSubject> {

    @Override
    public Boolean INSERT(InstructorSubject item) {
        Boolean result = false;
        String sql;
        if (item.getID_Teach() == -1){
            sql = "INSERT INTO `instructor_subject`(`ID_NUMBER`, `Subject_code`) VALUES ("+item.getID_NUMBER()+","+item.getSubject_code()+")";
        }
        else {
            sql = "INSERT INTO `instructor_subject`(`ID_Teach`,`ID_NUMBER`,`Subject_code`) VALUES ("+item.getID_Teach()+","+item.getID_NUMBER()+","+item.getSubject_code()+" )";
        }

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
    public List<InstructorSubject> SELECT(String sql) throws SQLException {
        List<InstructorSubject> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            InstructorSubject b = new InstructorSubject();
            b.setSubject_code(resultSet.getInt("Subject_code"));
            b.setID_NUMBER(resultSet.getInt("ID_NUMBER"));
            b.setID_Teach(resultSet.getInt("ID_Teach"));
            a.add(b);
        }
        JDBCDriver.DestroyConnection();
        return a;
    }

    @Override
    public Boolean UPDATE(InstructorSubject item) {
        Boolean result = false;
        String  sql= "UPDATE `instructor_subject` SET `ID_Teach`="+item.getID_Teach()+",`ID_NUMBER`="+item.getID_NUMBER()+",`Subject_code`="+item.getSubject_code()+" WHERE `ID_Teach`="+item.getID_Teach();
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
            JDBCDriver.SetQuery("DELETE FROM `teach_class` WHERE ID_Teach = "+id);
            boolean a =JDBCDriver.SetQuery("DELETE FROM `instructor_subject` WHERE `ID_Teach` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
