package DAO.Access;

import Model.Score;

import java.util.List;

public class ScoreStudentHandle extends AbsSQLAccess<Score> {
    protected ScoreStudentHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Score item) {

    }

    @Override
    public List<Score> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Score item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
