package Controllers.Authorization;

import Controllers.Authenlication.Authenlication;
import Controllers.Validation;
import DAO.JDBCDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Authorization {
    public static Boolean getPermision(String column){
        try {
            Boolean result = false;
            String sql = "SELECT i.ID_NUMBER,i.name,ir.Role_id,r."+column +
                    " FROM `instructor` AS i INNER JOIN instructor_role AS ir ON i.ID_NUMBER = ir.ID_NUMBER INNER JOIN role AS r ON ir.Role_id = r.id" +
                    " WHERE i.ID_NUMBER = "+ Authenlication.insLogin.getID_NUMBER();
            ResultSet rs  = JDBCDriver.ExecQuery(sql);
            while (rs.next()){
                if (rs.getBoolean(column)){
                    result = true;
                    break;
                }
            }
            JDBCDriver.DestroyConnection();
            if(result){
                System.out.println("allow");
            }else {
                System.out.println("denied");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Boolean getPermisionForStudent(){
        return getPermision("student_mngs");
    }
    public static Boolean getPermisionForTeacher(){
        return getPermision("teach_mngs");
    }
    public static Boolean getPermisionForBlock(){
        return getPermision("block_mngs");
    }
    public static Boolean getPermisionForClass(){
        return getPermision("class_mngs");
    }
    public static Boolean getPermisionForSubject(){
        return getPermision("subject_mngs");
    }
    public static Boolean getPermisionForAsignment(){
        return getPermision("asignment_mngs");
    }
    public static Boolean getPermisionForTypeScore(){
        return getPermision("type_score_mngs");
    }
    public static Boolean getPermisionForRole(){
        return getPermision("role_mngs");
    }
    public static Boolean getPermisionForPosition(){
        return getPermision("position_mngs");
    }
    public static Boolean getPermisionForScore(int subjectCode){
        String  sql = "SELECT i.ID_NUMBER,i.name,s.Subject_code,s.Name FROM instructor AS i JOIN instructor_role AS ir ON i.ID_NUMBER = ir.ID_NUMBER JOIN instructor_subject AS isubj ON i.ID_NUMBER = isubj.ID_NUMBER JOIN  subject AS s ON isubj.Subject_code = s.Subject_code " +
                "WHERE i.ID_NUMBER = "+Authenlication.insLogin.getID_NUMBER()+" AND s.Subject_code = "+subjectCode;
        try {
            Boolean result = false;
            ResultSet rs =  JDBCDriver.ExecQuery(sql);
            while (rs.next()){
                result = true;
                break;
            }
            JDBCDriver.DestroyConnection();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
