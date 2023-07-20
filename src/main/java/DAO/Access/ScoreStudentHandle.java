package DAO.Access;

import DAO.JDBCDriver;
import Model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreStudentHandle extends AbsSQLAccess<Score> {

    @Override
    public Boolean INSERT(Score item) {
        Boolean result = false;
        String sql = "INSERT INTO `score_student`(`ts_id`, `ss_id`, `ScoreValue`) VALUES ('"+item.getTypeScoreID()+"','"+item.getStudentSubjectID()+"','"+item.getScoreValue()+"')";
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
    public List<Score> SELECT(String sql) throws SQLException {
        List<Score> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Score b = new Score();
            b.setScoreID(resultSet.getInt("ScoreID"));
            b.setStudentSubjectID(resultSet.getInt("ss_id"));
            b.setScoreValue(resultSet.getDouble("ScoreValue"));
            b.setTypeScoreID(resultSet.getInt("ts_id"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Score item) {
        Boolean result = false;
        String  sql= "UPDATE `score_student` SET `ts_id`='"+item.getTypeScoreID()+"',`ss_id`='"+item.getStudentSubjectID()+"',`ScoreValue`='"+item.getScoreID()+"',`ScoreID`='"+item.getScoreID()+"' WHERE id="+item.getScoreID();
        String t = "INSERT INTO `MClass`(`ScoreID` , `ScoreValue`) VALUES ('"+item.getScoreID()+", '"+item.getScoreValue()+"')";
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
            boolean a =JDBCDriver.SetQuery("DELETE FROM `score_student` WHERE `ScoreID` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
