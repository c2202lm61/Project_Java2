package DAO;

import DAO.Access.ScoreStudentHandle;
import DAO.Access.SubjectStudentHandle;
import Model.Block;
import Model.Score;
import Model.SubjectStudent;
import Model.ViewScoreBeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ViewScore {
    public List<ViewScoreBeta> viewScoreBetaList(String sql){
        List<ViewScoreBeta> scoreBetaList = new ArrayList<>();

        try {
            ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            while (resultSet.next()){
                ViewScoreBeta viewScoreBeta = new ViewScoreBeta();
                viewScoreBeta.setStudentID(resultSet.getInt("Student_id"));
                viewScoreBeta.setStudentName(resultSet.getString("Student_name"));
                viewScoreBeta.setGrantID(resultSet.getInt("grant_id"));
                viewScoreBeta.setSubjectCode(resultSet.getInt("Subject_code"));
                viewScoreBeta.setClassCode(resultSet.getInt("class_code"));
                viewScoreBeta.setScoreValue(resultSet.getFloat("ScoreValue"));
                viewScoreBeta.setScoreType(resultSet.getInt("ScoreType"));
                scoreBetaList.add(viewScoreBeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scoreBetaList;
    }
    public boolean INSERT(int studentID, int subjectID, int typeScoreID, float Value){
        String sql = "SELECT * FROM `subject_student` WHERE student_id = "+studentID+" AND Subject_code = "+subjectID;
        List<SubjectStudent> subjectStudents = null;
        try {
            subjectStudents= new SubjectStudentHandle().SELECT(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int subjectStudentID;
        if(subjectStudents.size() == 0){
            SubjectStudent subjectStudent = new SubjectStudent();
            subjectStudent.setSubject_code(subjectID);
            subjectStudent.setStudent_id(studentID);
            subjectStudent.setSubject_Student_id(-1);
            new SubjectStudentHandle().INSERT(subjectStudent);
            try {
                subjectStudents= new SubjectStudentHandle().SELECT(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            subjectStudentID = subjectStudents.get(0).getSubject_Student_id();
        }else {
            subjectStudentID = subjectStudents.get(0).getSubject_Student_id();
        }
        Score score = new Score();
        score.setScoreValue(Value);
        score.setStudentSubjectID(subjectStudentID);
        score.setTypeScoreID(typeScoreID);
        score.setScoreID(-1);
        return new ScoreStudentHandle().INSERT(score);
    }
    public boolean DELETE(ViewScoreBeta tempViewScore){
        String sql;
        int subjectStudentID;
        try {
            sql = "SELECT * FROM `subject_student` WHERE student_id = "+tempViewScore.getStudentID()+" AND Subject_code = "+tempViewScore.getSubjectCode();
            final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            if (resultSet.next()) {
                subjectStudentID = resultSet.getInt("Subject_student_id");
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int countnumber = 0;
        try {
            sql = "SELECT COUNT(ScoreID) AS numberScore FROM `score_student` WHERE `ss_id` = "+subjectStudentID;
            final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            if (resultSet.next()) {
                countnumber = resultSet.getInt("numberScore");
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (countnumber == 0){
            return new SubjectStudentHandle().DELETE(subjectStudentID);
        }else if(countnumber > 0) {
            int ScoreID;
            try {
                sql = "SELECT TOP 1 `ScoreID`  FROM `score_student` WHERE `ts_id` "+tempViewScore.getScoreType()+" AND `ss_id` = "+subjectStudentID+ " AND `ScoreValue` +" +tempViewScore.getScoreValue();
                final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
                if(resultSet.next()){
                    ScoreID = resultSet.getInt("ScoreID");
                }else {
                    return false;
                }
                return  new ScoreStudentHandle().DELETE(ScoreID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }
    public boolean UPDATE(ViewScoreBeta tempViewScore,int typescoreID, float Value){
        String sql;
        int subjectStudentID;
        try {
            sql = "SELECT * FROM `subject_student` WHERE student_id = "+tempViewScore.getStudentID()+" AND Subject_code = "+tempViewScore.getSubjectCode();
            final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            if (resultSet.next()) {
                subjectStudentID = resultSet.getInt("Subject_student_id");
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int ScoreID;
        try {
            sql = "SELECT `ScoreID`  FROM `score_student` WHERE `ts_id` = "+tempViewScore.getScoreType()+" AND `ss_id` = "+subjectStudentID+ " AND `ScoreValue` ="+ tempViewScore.getScoreValue() +" LIMIT 1" ;
            final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
            if(resultSet.next()){
                ScoreID = resultSet.getInt("ScoreID");
            }else {
                return false;
            }
            Score score = new Score();
            score.setTypeScoreID(typescoreID);
            score.setScoreID(ScoreID);
            score.setStudentSubjectID(subjectStudentID);
            score.setScoreValue(Double.valueOf(Value));

            return  new ScoreStudentHandle().UPDATE(score);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
