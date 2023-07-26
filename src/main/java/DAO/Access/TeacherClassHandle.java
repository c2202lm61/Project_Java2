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
        Boolean result = false;
        String sql;
        if(item.getClass_code() == -1){sql = "INSERT INTO `teach_class`(`numberofsemester`, `Class_code`, `ID_Teach`) VALUES ('"+item.getNumberofsemester()+"',"+item.getClass_code()+","+item.getID_Teach()+")";}
        else {sql = "INSERT INTO `teach_class`(`id_tc`,`numberofsemester`, `Class_code`, `ID_Teach`) VALUES ('"+item.getClass_code()+"','"+item.getNumberofsemester()+"'," +
                ""+item.getClass_code()+","+item.getID_Teach()+")";}

        try {
            boolean a = JDBCDriver.SetQuery(sql);
            System.out.println("thêm dữ .iệu thành công "+a);
            result = true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
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
           b.setNumberofsemester(resultSet.getString("numberofsemester"));
        a.add(b);
       }
    return a;
    }

    @Override
    public Boolean UPDATE(TeacherClass item) {

        Boolean result = false;
        String  sql= "UPDATE `teach_class` SET `id_tc`="+item.getId_tc()+",`numberofsemester`='"+item.getNumberofsemester()+"',`Class_code`="+item.getClass_code()+",`ID_Teach`="+item.getID_Teach()+" WHERE id="+item.getId_tc();
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
        Boolean result = false;
        try {
            boolean a =JDBCDriver.SetQuery("DELETE FROM `teach_class` WHERE `id_tc` = "+id);
            if (a)System.out.println("Xóa dữ liệu thành công");
            else System.out.println("Dữ liệu đó không tồn tại");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
