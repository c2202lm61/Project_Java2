package DAO.Access;

import DAO.JDBCDriver;
import Model.Instructor;
import Model.InstructorRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorRoleHandle  extends AbsSQLAccess<InstructorRole> {

    @Override
    public Boolean INSERT(InstructorRole item) {
        String sql =  "INSERT INTO `instructor_role`( `Role_id`, `ID_NUMBER`) VALUES ("+item.getRole_id()+","+item.getID_NUMBER()+")";
        try {
           return JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<InstructorRole> SELECT(String sql){
        List<InstructorRole> instructorRoleList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCDriver.ExecQuery(sql);
            while (rs.next()){
                instructorRoleList.add(new InstructorRole(rs.getInt("id"), rs.getString("role_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBCDriver.DestroyConnection();
        return instructorRoleList;
    }

    @Override
    public Boolean UPDATE(InstructorRole item) {
        String sql = "";
        try {
            return JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean DELETE(int id) {
        String sql = "DELETE FROM `instructor_role` WHERE id =  "+id;
        try {
            return JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
