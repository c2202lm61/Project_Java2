package DAO.Access;

import DAO.JDBCDriver;
import Model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentHandle extends AbsSQLAccess<Student>{

    @Override
    public Boolean INSERT(Student item) {
        return null;
    }

    @Override
    public List<Student> SELECT(String sql) throws SQLException {
        List<Student> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Student b = new Student();
            b.setID(resultSet.getInt("Student_id"));
            b.setName(resultSet.getString("Name"));
            b.setSocialSecurtyNumber(resultSet.getString("Social_Securty_Number"));
            b.setBirthday(resultSet.getDate("Birthday"));
            b.setGender(resultSet.getBoolean("Gender"));
            b.setAddress(resultSet.getString("Current_address"));
            b.setAddress(resultSet.getString("Phone"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Student item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
