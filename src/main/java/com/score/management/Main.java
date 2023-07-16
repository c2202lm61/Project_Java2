package com.score.management;
import DAO.Access.GrantHandle;
import DAO.JDBCDriver;
import GUI.*;
import Model.Block;
import Model.Student;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        Login login =new Login();
//        MainGUI mainGUI = new MainGUI();
        JFrame frame = new JFrame();
        frame.setSize(300,200);

        GrantHandle grantHandle = new GrantHandle();
        List<Block> a = null;
        try {
            a = grantHandle.SELECT("SELECT * FROM grants");
            Block d = a.get(0);
            System.out.println(d.Name);
            System.out.println(d.ID);
            frame.add(new JButton(d.Name));
            frame.setVisible(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
