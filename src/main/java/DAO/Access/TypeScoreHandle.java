package DAO.Access;

import Model.TypeScore;

import java.util.List;

public class TypeScoreHandle extends AbsSQLAccess<TypeScore> {

    protected TypeScoreHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(TypeScore item) {

    }

    @Override
    public List<TypeScore> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(TypeScore item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
