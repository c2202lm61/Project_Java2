package DAO;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCDriver{
    static Connection conn = null;
    static ResultSet resultSet  = null;
    static Statement  stmt = null;
    static ResourceBundle a;
    public static ResultSet ExecQuery(String sql) throws SQLException {
        a = ResourceBundle.getBundle("projectdata");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ a.getString("database"),a.getString("username"),a.getString("password"));
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
    public static void SetQuery(String sql) throws SQLException {
        a = ResourceBundle.getBundle("projectdata");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ a.getString("database"),a.getString("username"),a.getString("password"));
            PreparedStatement statement = conn.prepareStatement(sql);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Database connection successfull");
      //      System.out.println("Số dòng bị xóa: " + rowsAffected);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
