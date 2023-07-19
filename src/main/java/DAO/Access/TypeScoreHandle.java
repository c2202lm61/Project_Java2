package DAO.Access;

import DAO.JDBCDriver;
import Model.TypeScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeScoreHandle extends AbsSQLAccess<TypeScore> {
    @Override
    public Boolean INSERT(TypeScore item) {
        return null;
    }

    @Override
    public List<TypeScore> SELECT(String sql) throws SQLException {
       List<TypeScore> a = new ArrayList<>();
       final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
       while (resultSet.next()){
           TypeScore b = new TypeScore();
           b.setID(resultSet.getInt("id"));
           b.setName(resultSet.getString("name"));
           a.add(b);
       }
       return a;
    }

    @Override
    public Boolean UPDATE(TypeScore item) {
        Boolean result = false;
        String  sql= "UPDATE `type_score` SET `ts_id`='"+item.getID()+"',`name`='"+item.getName()+"' WHERE id="+item.getID();
        System.out.println(sql);
        try {
            boolean a =JDBCDriver.SetQuery(sql);
            if (a)System.out.println("Cập nhật dữ liệu thành công");
            else System.out.println("Cập nhật dữ liệu không thành công");
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
