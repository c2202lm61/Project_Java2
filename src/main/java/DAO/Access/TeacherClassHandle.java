package DAO.Access;

import Model.Teacher;
import Model.TeacherClass;

import java.util.List;

public class TeacherClassHandle extends AbsSQLAccess<TeacherClass> {

    protected TeacherClassHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(TeacherClass item) {

    }

    @Override
    public List<TeacherClass> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(TeacherClass item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
