package Controllers;

import DAO.Access.AbsSQLAccess;
import DAO.Access.ClassHandle;
import DAO.Access.StudentHandle;
import DAO.JDBCDriver;
import Model.MClass;
import Model.Student;
import Model.ViewScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewScoreController  {

    public List<ViewScore> SELECT(int MaKHoi, int MaLop, int MaMH) throws SQLException {

        List<ViewScore> a= null;
        float total = 0;
        int count = 0;
        String sql ="";
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        return a;
    }


}
