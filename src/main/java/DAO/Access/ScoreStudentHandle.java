package DAO.Access;

import Model.Score;

import java.sql.SQLException;
import java.util.List;

public class ScoreStudentHandle extends AbsSQLAccess<Score> {

    @Override
    public Boolean INSERT(Score item) {
        return null;
    }

    @Override
    public List<Score> SELECT(String sql) throws SQLException {
        return null;
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
