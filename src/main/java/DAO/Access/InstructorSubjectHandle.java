package DAO.Access;

import DAO.JDBCDriver;
import Model.InstructorRole;
import Model.InstructorSubject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorSubjectHandle extends AbsSQLAccess<InstructorSubject> {

    @Override
    public Boolean INSERT(InstructorSubject item) {
        return null;
    }

    @Override
    public List<InstructorSubject> SELECT(String sql) throws SQLException {
        List<InstructorSubject> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            InstructorSubject b = new InstructorSubject();
            b.setSubject_code(resultSet.getInt("Subject_code"));
            b.setID_NUMBER(resultSet.getInt("ID_NUMBER"));
            b.setID_Teach(resultSet.getInt("ID_Teach"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(InstructorSubject item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
