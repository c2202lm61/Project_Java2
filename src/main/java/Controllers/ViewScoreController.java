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

        List<ViewScore> a = new ArrayList<>();


        StudentHandle studentHandle = new StudentHandle();

        List<Student> students = null;
                try {
                    students = studentHandle.SELECT("SELECT student.Name, student.Class_code, subject_student.Subject_code,score_student.ScoreValue,score_student.ts_id FROM `student`  INNER JOIN subject_student ON subject_student.student_id = student.Student_id ");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()){
            Student student = studentIterator.next();

        }



        return a;
    }


}
