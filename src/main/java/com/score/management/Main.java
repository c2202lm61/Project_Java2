package com.score.management;
import GUI.HomePage;
import GUI.Register;
import GUI.Login;
import GUI.MainGUI;

import java.util.ResourceBundle;
public class Main {
    public static void main(String[] args) {

        MainGUI mainGUI = new MainGUI();
        Register register = new Register();
        Login login =new Login();
        HomePage homePage = new HomePage();
        homePage.Home();
        ResourceBundle a = ResourceBundle.getBundle("projectdata");
        System.out.println(a.getString("database"));
    }
}
