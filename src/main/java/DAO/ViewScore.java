package DAO;

import Model.Block;
import Model.ViewScoreBeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewScore {
    public static List<ViewScoreBeta> viewScoreBetaList(int grant_id,int classCode){
        System.out.println("Lop" + classCode);
        List<ViewScoreBeta> scoreBetaList = new ArrayList<>();
        String sql ="SELECT s.Student_id,s.Name AS Student_name, c.class_code, c.grant_id, ss.Subject_code, COALESCE(score.ScoreValue, NULL) AS ScoreValue, ts.name AS ScoreType " +
                "FROM student AS s LEFT JOIN class AS c ON s.Class_code = c.class_code LEFT JOIN grants AS g ON c.grant_id = g.id LEFT JOIN subject_student AS ss ON s.Student_id = ss.student_id LEFT JOIN score_student AS score ON ss.Subject_student_id = score.ss_id LEFT JOIN type_score AS ts ON score.ts_id = ts.ts_id " +
                "WHERE g.id = "+grant_id +" AND c.class_code = "+classCode;
        try {
            ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            while (resultSet.next()){
                ViewScoreBeta viewScoreBeta = new ViewScoreBeta();
                viewScoreBeta.setStudentID(resultSet.getInt("Student_id"));
                viewScoreBeta.setStudentName(resultSet.getString("Student_name"));
                viewScoreBeta.setGrantID(resultSet.getInt("grant_id"));
//                viewScoreBeta.setSubjectCode(resultSet.getInt("Subject_code"));
                viewScoreBeta.setClassCode(resultSet.getInt("class_code"));
//                viewScoreBeta.setScoreValue(resultSet.getInt("ScoreValue"));
//                viewScoreBeta.setScoreType(resultSet.getInt("ScoreType"));
                scoreBetaList.add(viewScoreBeta);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scoreBetaList;
    }
}
