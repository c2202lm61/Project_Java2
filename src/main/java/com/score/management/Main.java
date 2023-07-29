package com.score.management;

import Controllers.Authenlication.Authenlication;

import DAO.Access.*;
import GUI.*;
import GUI.MainGUIComponents.Popup;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.Validation;
import DAO.Access.InstructorHandle;
import DAO.ViewScore;
import GUI.Login;
import GUI.MainGUI;
import GUI.Register;
import Model.Instructor;
import Model.ViewScoreBeta;


import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        new Login();
     //   new Register();
        new MainGUI();
//        new Popup();

//        List<ViewScoreBeta> viewScore = ViewScore.viewScoreBetaList();
//        Iterator<ViewScoreBeta> viewScoreBetaIterator = viewScore.iterator();
//        while (viewScoreBetaIterator.hasNext()){
//           ViewScoreBeta viewScoreBeta = viewScoreBetaIterator.next();
//            System.out.println("studentID:"+viewScoreBeta.getStudentID());
//            System.out.println("studentName"+viewScoreBeta.getStudentName());
//            System.out.println("ClassCode"+viewScoreBeta.getClassCode());
//            System.out.println("GrantID"+viewScoreBeta.getGrantId());
//            System.out.println("GrantName"+viewScoreBeta.getGrantName());
//            System.out.println("SubjectStudentID"+viewScoreBeta.getSubjectStudentID());
//        }
    }


}

