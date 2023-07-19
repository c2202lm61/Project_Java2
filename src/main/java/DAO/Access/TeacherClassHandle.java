package DAO.Access;

import DAO.JDBCDriver;
import Model.TeacherClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherClassHandle extends AbsSQLAccess<TeacherClass> {


    @Override
    public Boolean INSERT(TeacherClass item) {
        return null;
    }

    @Override
    public List<TeacherClass> SELECT(String sql) throws SQLException {
       List<TeacherClass> a = new ArrayList<>();
       final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
       while(resultSet.next()){
           TeacherClass b = new TeacherClass();
           b.setClass_code(resultSet.getInt("Class_code"));
           b.setId_tc(resultSet.getInt("ID_tc"));
           b.setID_Teach(resultSet.getInt("ID_Teach"));
           b.setNumberofsemester(resultSet.getInt("numberofsemester"));
a.add(b);
       }
    return a;
    }

    @Override
    public Boolean UPDATE(TeacherClass item) {

        Boolean result = false;
        String  sql= "UPDATE `teach_class` SET `id_tc`='"+item.getId_tc()+"',`numberofsemester`='"+item.getNumberofsemester()+"',`Class_code`='"+item.getClass_code()+"',`ID_Teach`='"+item.getID_Teach()+"' WHERE id="+item.getId_tc();
        System.out.println(sql);
        try {
            boolean a =JDBCDriver.SetQuery(sql);
            if (a)System.out.println("Cập nhật dữ liệu thành công");
            else System.out.println("Cập nhật dữ liệu không thành công");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
