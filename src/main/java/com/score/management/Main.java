package com.score.management;
import DAO.Access.*;
import GUI.*;
import Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.setName("quan");
//        student.setBirthday(LocalDate.now());
//        student.setAddress("175 tay son");
//        student.setGender(true);
//        student.setPhone("01234567");
//        student.setClassID(1);
//        student.setSocialSecurtyNumber("01234567");
//        new StudentHandle().INSERT(student);
        new MainGUI();
    }
}
