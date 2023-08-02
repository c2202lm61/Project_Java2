package Controllers;

import DAO.JDBCDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^*]).{8,}$");
    public static Boolean isEmail(String email){

        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static Boolean isStrongPassword(String password){

        return PASSWORD_PATTERN.matcher(password).matches();
    }
    public static boolean isFullName(String fullname) {
        String regex = "^[\\p{L}\\s]+$";

        return Pattern.matches(regex, fullname);
    }
    public static Boolean isPhone(String  phone){

        return phone.matches("[0-9]{8}");
    }
    // check birthday is valid
    public static Boolean isBirthday(LocalDate birthday){

        return birthday.isBefore(LocalDate.now());
    }
    // chech age is valid
    public static Boolean checkAge(int age,int year){

        LocalDate birthday = null;
        return year - birthday.getYear() == age;
    }
    //
    public static Boolean checkLogin(String email, String password) {
        return isEmail(email) && isStrongPassword(password);
    }

    //check has email
    public static Boolean hasEmail(String email) throws SQLException {
        ResultSet resultSet = JDBCDriver.ExecQuery("SELECT * FROM instructor WHERE Email = "+"'"+email+"'");
        if(resultSet.next()) return true;
        else return false;
    }
}

