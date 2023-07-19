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
        return null;
    }

    @Override
    public List<SubjectStudent> SELECT(String sql) throws SQLException {
        List<SubjectStudent> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            SubjectStudent b = new SubjectStudent();
            b.setSubject_Student_id(resultSet.getInt("Subject_student_id\tstudent_id\tSubject_code"));
            b.setStudent_id(resultSet.getInt("student_id"));
            b.setSubject_code(resultSet.getInt("Subject_code"));
            a.add(b);

        }
        return a;
    }

    @Override
    public Boolean UPDATE(SubjectStudent item) {
        Boolean result = false;
        String  sql= "UPDATE `subject_student` SET `Subject_student_id`='"+item.getSubject_Student_id()+"',`student_id`='"+item.getStudent_id()+"',`Subject_code`='"+item.getSubject_code()+"' WHERE id="+item.getSubject_Student_id();
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
