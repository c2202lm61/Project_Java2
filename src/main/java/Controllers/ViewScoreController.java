package Controllers;

import DAO.Access.AbsSQLAccess;
import DAO.Access.ClassHandle;
import DAO.Access.StudentHandle;
import DAO.JDBCDriver;
import Model.MClass;
import Model.Student;
import Model.ViewScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewScoreController  {

    public List<ViewScore> SELECT(int MaKHoi, int MaLop, int MaMH) throws SQLException {

        List<ViewScore> a= null;
        float total = 0;
        int count = 0;
        String sql = "SELECT class.grant_id,class.class_code,student.Name,student.Student_id,subject_student.Subject_student_id,subject_student.Subject_code FROM class INNER JOIN student on student.Class_code = class.class_code INNER JOIN subject_student on student.Student_id = subject_student.student_id WHERE class.grant_id = "+MaKHoi+" AND class.class_code = "+MaLop+" AND subject_student.Subject_code = "+MaMH+"";
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            ViewScore b = new ViewScore();
            b.setMaHocSinh(resultSet.getInt("student.Student_id"));
            b.setMaLop(resultSet.getInt("class.class_code"));
            b.setTenHS(resultSet.getString("student.Name"));
            b.setMaKhoi(resultSet.getInt("class.grant_id"));
            b.setMaMon(resultSet.getInt("subject_student.Subject_code"));
            b.setMaDiem(resultSet.getInt("subject_student.Subject_student_id"));
            b.setDHS1();
            b.setDHS2();
            b.setDHS3();
            b.setDHS4();
            b.setTongDien();
            a.add(b);
        }
        return a;
    }


}
