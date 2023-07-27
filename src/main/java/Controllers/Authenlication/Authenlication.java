package Controllers.Authenlication;

import Controllers.Validation;
import DAO.Access.InstructorHandle;
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

        while (resultSet.next()){
            if(email.equals(resultSet.getString("Email")) && password.equals(resultSet.getString("Password"))){
                new MainGUI();
            }else {
                System.out.println("Thông tin tài khoản hoặc mât khẩu không chính xác");
            }
            return;
        }
        System.out.println("Email không tồn tại");
        //do something
    }
    public static void Register(String email,String password, String fullname){
        if (!Validation.isFullName(fullname)){
            System.out.println("Your fullname is not valid");
            return;
        }
        if(!Validation.isEmail(email)){
            System.out.println("Email is not valid");
            return;
        }
        if(!Validation.isStrongPassword(password)){
            System.out.println("Password is not valid");
            return;
        }
        try {
            if(Validation.hasEmail(email)){System.out.println("Email is has exist"); return;}
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
          Boolean result =  JDBCDriver.SetQuery("INSERT INTO `instructor`(`name`, `password`, `Email`,`Phone`) VALUES ('"+fullname+"','"+password+"','"+email+"','01234567')");
          if(result){
              System.out.println("Thêm dữ liệu thành công");
          }else {
              System.out.println("Thêm dữ liệu không thành công");
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void ForgotPassword(){
        
    }
}
