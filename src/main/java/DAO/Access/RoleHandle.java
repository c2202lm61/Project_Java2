package DAO.Access;

import DAO.JDBCDriver;
import Model.InstructorRole;
import Model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoleHandle extends AbsSQLAccess<Role>{


    @Override
    public Boolean INSERT(Role item) {
        String sql = "INSERT INTO `role`" +
                "( `role_name`, `student_mngs`, `teach_mngs`, `block_mngs`, `class_mngs`, `subject_mngs`, `asignment_mngs`, `type_score_mngs`, `role_mngs`, `position_mngs`) VALUES " +
                "('"+item.getName()+"" +
                "',"+item.getStudent_mngs() +
                ","+item.getTeach_mngs() +
                ","+item.getBlock_mngs() +
                ","+item.getClass_mngs() +
                ","+item.getSubject_mngs() +
                ","+item.getAsignment_mngs() +
                ","+item.getTypescore_mngs() +
                ","+item.getRole_mngs() +
                ","+item.getPosition_mngs() +
                ")";
        Boolean result =  true;
        try {
             result = JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sql);
        return result;
    }

    @Override
    public List<Role> SELECT(String sql) {
        List<Role> roleList = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCDriver.ExecQuery(sql);
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("role_name"));
                role.setStudent_mngs(rs.getBoolean("student_mngs"));
                role.setTeach_mngs(rs.getBoolean("teach_mngs"));
                role.setBlock_mngs(rs.getBoolean("block_mngs"));
                role.setClass_mngs(rs.getBoolean("class_mngs"));
                role.setSubject_mngs(rs.getBoolean("subject_mngs"));
                role.setAsignment_mngs(rs.getBoolean("asignment_mngs"));
                role.setTypescore_mngs(rs.getBoolean("type_score_mngs"));
                role.setRole_mngs(rs.getBoolean("role_mngs"));
                role.setPosition_mngs(rs.getBoolean("position_mngs"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBCDriver.DestroyConnection();
        return roleList;
    }

    @Override
    public Boolean UPDATE(Role item) {
        Boolean result = false;
        String sql = "UPDATE `role` SET " +
                "`id`="+item.getId()+"," +
                "`role_name`='"+item.getName()+"'," +
                "`student_mngs`="+item.getStudent_mngs()+"," +
                "`teach_mngs`="+item.getTeach_mngs()+"," +
                "`block_mngs`="+item.getBlock_mngs()+"," +
                "`class_mngs`="+item.getClass_mngs()+"," +
                "`subject_mngs`="+ item.getSubject_mngs()+"," +
                "`asignment_mngs`="+ item.getAsignment_mngs()+"," +
                "`type_score_mngs`="+item.getTypescore_mngs()+"," +
                "`role_mngs`="+item.getRole_mngs()+"," +
                "`position_mngs`="+item.getPosition_mngs() +
                " WHERE id = "+item.getId();
        try {
           result = JDBCDriver.SetQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Boolean DELETE(int id) {
        Boolean  result;
        try {
            result = JDBCDriver.SetQuery("DELETE FROM `role` WHERE id = "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
