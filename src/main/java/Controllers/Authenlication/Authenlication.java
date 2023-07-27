package Controllers.Authenlication;

import Controllers.Validation;
import DAO.JDBCDriver;
import GUI.MainGUI;
import Model.Instructor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenlication {
    //Session Login
    public static Instructor InstructorAccess;
    public static void Login(String email, String password) throws SQLException {
        if(!Validation.isEmail(email)){
            System.out.println("Email is not valid"); return;
        }
        if(!Validation.isStrongPassword(password)){
            System.out.println("Password is not valid"); return;
        }
        ResultSet resultSet =JDBCDriver.ExecQuery("SELECT * FROM instructor WHERE Email = "+"'"+email+"'");
        if(!resultSet.next()){
            System.out.println("Email is not exist");  return;
        }else {
            System.out.println("Email has exist");
        }

        //do something
    }
    public static void Register(String email,String password, String phone){
        //do something
    }
    public static void ForgotPassword(){
        
    }
}
