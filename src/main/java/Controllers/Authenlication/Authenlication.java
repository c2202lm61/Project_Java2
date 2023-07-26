package Controllers.Authenlication;

import DAO.JDBCDriver;
import Model.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenlication {
    //Session Login
    public static Instructor InstructorAccess;
    public static void Login(String email, String password) throws SQLException {
        ResultSet resultSet =JDBCDriver.ExecQuery("SELECT * FROM instructor WHERE Email = "+"'"+email+"'");
        //do something
    }
    public static void Register(String email,String password, String phone){
        //do something
    }
    public static void ForgotPassword(){
        
    }
}
