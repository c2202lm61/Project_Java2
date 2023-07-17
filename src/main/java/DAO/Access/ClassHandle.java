package DAO.Access;

import DAO.JDBCDriver;
import Model.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassHandle extends AbsSQLAccess{


    @Override
    public Boolean INSERT(Object item) {
        return null;
    }

    @Override
    public List<Class> SELECT(String sql) throws SQLException {
        List<Class> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Class b = new Class();
            b.setID(resultSet.getInt("class_code"));
            b.setGrandID(resultSet.getInt("grand_id"));
            b.setManagerID(resultSet.getInt("ID_manager"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Object item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
