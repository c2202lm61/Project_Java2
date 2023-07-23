package com.score.management;
import DAO.Access.*;
import DAO.JDBCDriver;
import GUI.*;
import GUI.MainGUIComponents.ManageComponent.BlockManagement;
import Model.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Instructor instructor = new Instructor();
        instructor.setName("Pham quan");
        instructor.setPassword("qwert");
        instructor.setGender(true);
        LocalDateTime localDate = LocalDateTime.now();
        instructor.setBirthday(localDate.toLocalDate());
        instructor.setEmail("abc@gmail.com");
        instructor.setPhone("01234561");
        new InstructorHandle().INSERT(instructor);

        new MainGUI();


    }
}
