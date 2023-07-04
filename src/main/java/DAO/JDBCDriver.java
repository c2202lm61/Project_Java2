package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCDriver {
    Connection conn = null;

    public JDBCDriver(){
        ResourceBundle a = ResourceBundle.getBundle("projectdata");
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ a.getString("database"),a.getString("username"),a.getString("password"));
            System.out.println("Ket  noi database thanh cong");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("Ket  noi database that bai");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
