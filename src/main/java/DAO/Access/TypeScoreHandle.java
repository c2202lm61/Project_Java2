package DAO.Access;

import DAO.JDBCDriver;
import Model.TypeScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeScoreHandle extends AbsSQLAccess<TypeScore> {
    @Override
    public Boolean INSERT(TypeScore item) {
        return null;
    }

    @Override
    public List<TypeScore> SELECT(String sql) throws SQLException {
       List<TypeScore> a = new ArrayList<>();
       final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
       while (resultSet.next()){
           TypeScore b = new TypeScore();
           b.setID(resultSet.getInt("id"));
           b.setName(resultSet.getString("name"));
           a.add(b);
       }
       return a;
    }

    @Override
    public Boolean UPDATE(TypeScore item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
