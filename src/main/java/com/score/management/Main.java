package com.score.management;
import DAO.Access.*;
import DAO.JDBCDriver;
import GUI.*;
import GUI.MainGUIComponents.ManageComponent.BlockManagement;
import Model.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        new MainGUI();
    }
}
