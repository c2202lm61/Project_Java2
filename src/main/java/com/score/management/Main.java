package com.score.management;
import DAO.JDBCDriver;
import GUI.HomePage;

public class Main {
    public static void main(String[] args) {
        HomePage homePage  = new HomePage();
        homePage.ShowHomePage();
        JDBCDriver connectMysql = new JDBCDriver();
    //    MainGUI mainGUI  = new MainGUI();
//        Register register = new Register();
//        Login login =new Login();

    }
}
