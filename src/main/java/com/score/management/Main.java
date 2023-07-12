package com.score.management;
import DAO.JDBCDriver;
import GUI.*;


public class Main {
    public static void main(String[] args) {
        JDBCDriver connectMysql = new JDBCDriver();
        MainGUI mainGUI  = new MainGUI();
        Register register = new Register();
        Login login =new Login();

    }
}
