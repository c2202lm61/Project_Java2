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
        new MainGUI();
    }
}