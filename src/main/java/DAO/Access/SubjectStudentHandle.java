package DAO.Access;

import DAO.JDBCDriver;
import Model.SubjectStudent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectStudentHandle extends AbsSQLAccess<SubjectStudent> {

    @Override
    public Boolean INSERT(SubjectStudent item) {
        Boolean result = false;
        String sql;
        if(item.getSubject_Student_id() == -1){
            sql = "INSERT INTO `subject_student`(`student_id`, `Subject_code`) VALUES ("+item.getStudent_id()+","+item.getSubject_code()+")";
        }else {
            sql = "INSERT INTO `subject_student`(`Subject_student_id`, `student_id`, `Subject_code`) VALUES ("+item.getSubject_Student_id()+","+item.getStudent_id()+","+item.getSubject_code()+")";
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
    public List<SubjectStudent> SELECT(String sql) throws SQLException {
        List<SubjectStudent> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            SubjectStudent b = new SubjectStudent();
            b.setSubject_Student_id(resultSet.getInt("Subject_student_id"));
            b.setStudent_id(resultSet.getInt("student_id"));
            b.setSubject_code(resultSet.getInt("Subject_code"));
            a.add(b);

        }
        return a;
    }

    @Override
    public Boolean UPDATE(SubjectStudent item) {
        Boolean result = false;
        String  sql= "UPDATE `subject_student` SET `Subject_student_id`="+item.getSubject_Student_id()+",`student_id`="+item.getStudent_id()+",`Subject_code`="+item.getSubject_code()+" WHERE `Subject_student_id`="+item.getSubject_Student_id();
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
            boolean a =JDBCDriver.SetQuery("DELETE FROM `subject_student` WHERE `Subject_student_id` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
