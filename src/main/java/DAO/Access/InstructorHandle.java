package DAO.Access;

import DAO.JDBCDriver;
import Model.Block;
import Model.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorHandle  extends AbsSQLAccess<Instructor>{


    @Override
    public Boolean INSERT(Instructor item) {
        return null;
    }

    @Override
    public List<Instructor> SELECT(String sql) throws SQLException {

    List<Instructor> a = new ArrayList<>();
    final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
    while (resultSet.next()){
        Instructor b = new Instructor();
        b.setID_NUMBER(resultSet.getInt("ID_NUMBER"));
        b.setName(resultSet.getString("name"));
        b.setBirthday(resultSet.getDate("birthday"));
        b.setGender(resultSet.getBoolean("gender"));
        b.setPassword(resultSet.getString("passwrod "));
        a.add(b);
        }
    return a;

    }

    @Override
    public Boolean UPDATE(Instructor item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
