package DAO.Access;

import Model.Block;
import Model.Instructor;

import java.util.List;

public class InstructorHandle  extends AbsSQLAccess<Instructor>{

    protected InstructorHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Instructor item) {

    }

    @Override
    public List<Instructor> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Instructor item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
