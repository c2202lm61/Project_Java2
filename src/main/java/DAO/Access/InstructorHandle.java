package DAO.Access;

import Model.Block;
import Model.Instructor;

import java.sql.SQLException;
import java.util.List;

public class InstructorHandle  extends AbsSQLAccess<Instructor>{


    @Override
    public Boolean INSERT(Instructor item) {
        return null;
    }

    @Override
    public List<Instructor> SELECT(String sql) throws SQLException {
        return null;
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
