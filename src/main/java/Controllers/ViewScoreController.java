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

    public List<ViewScore> SELECT(String sql) throws SQLException {

        List<ViewScore> a= new ArrayList<>();
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
        JDBCDriver.DestroyConnection();
        return a;
    }


}
