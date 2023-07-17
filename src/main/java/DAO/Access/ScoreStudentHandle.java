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
        return null;
    }

    @Override
    public List<Score> SELECT(String sql) throws SQLException {
        List<Score> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Score b = new Score();
            b.setStudentSubjectID(resultSet.getInt("ss_id"));
            b.setScoreValue(resultSet.getDouble("ScoreValue"));
            b.setTypeScoreID(resultSet.getInt("tc_id"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Score item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
