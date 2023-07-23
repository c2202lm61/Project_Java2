package com.score.management;
import DAO.Access.*;
import GUI.*;
import Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
//        Instructor instructor = new Instructor();
//        instructor.setName("Pham Qun depzaivkl");
//        instructor.setPassword("qqqq111");
//        instructor.setGender(true);
//        String dateString = "2000-11-13";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(dateString, formatter);
//        instructor.setBirthday(date);
//        instructor.setEmail("abc@gmail.com");
//        instructor.setPhone("01234561");
//        new InstructorHandle().INSERT(instructor);

        new MainGUI();


    }
}
