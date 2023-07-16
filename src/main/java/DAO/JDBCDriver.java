package DAO;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCDriver{
    static Connection conn = null;
    static ResultSet resultSet  = null;
    static Statement  stmt = null;
    public static ResultSet ExecQuery(String sql) throws SQLException {
        ResourceBundle a = ResourceBundle.getBundle("projectdata");
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ a.getString("database"),a.getString("username"),a.getString("password"));
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            System.out.println("Database connection successfull");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("Database connection fail");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return resultSet;
    }
}
