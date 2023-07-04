package com.score.management;
import GUI.HomePage;
import GUI.Register;
import GUI.Login;
import GUI.MainGUI;

import java.util.ResourceBundle;
public class Main {
    public static void main(String[] args) {
//        HomePage homePage  = new HomePage();
//        homePage.ShowHomePage();
        MainGUI mainGUI = new MainGUI();
//        Register register = new Register();
//        Login login =new Login();
        ResourceBundle a = ResourceBundle.getBundle("projectdata");
        System.out.println(a.getString("database"));
    }
}
