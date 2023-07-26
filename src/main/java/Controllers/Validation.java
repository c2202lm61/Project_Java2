package Controllers;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^*]).{8,}$");
    public static Boolean checkEmail(String email){

        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static Boolean checkPassword(String password){

        return PASSWORD_PATTERN.matcher(password).matches();
    }
    public static Boolean checkFullName(String fullname){

        return fullname.matches("[a-zA-Z ]+");
    }
    public static Boolean checkPhone(String  phone){

        return phone.matches("[0-9]{10}");
    }
    public static Boolean checkBirthday(LocalDate birthday){

        return birthday.isBefore(LocalDate.now());
    }
    public static Boolean checkAge(int age,int year){

        LocalDate birthday = null;
        return year - birthday.getYear() == age;
    }
    public static Boolean checkLogin(String email, String password) {
        return checkEmail(email) && checkPassword(password);
    }
}

