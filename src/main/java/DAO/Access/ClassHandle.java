package DAO.Access;

import DAO.MySQLSupport;
import Model.MClass;

import java.sql.SQLException;
import java.util.List;

public class ClassHandle extends AbsSQLAccess<MClass>{
    @Override
    public Boolean INSERT(MClass item) {

        return null;
    }

    @Override
    public List<MClass> SELECT(String sql) throws SQLException {

        return null;
    }

    @Override
    public Boolean UPDATE(MClass item) {

        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
