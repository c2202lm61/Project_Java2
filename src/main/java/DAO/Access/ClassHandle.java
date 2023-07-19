package DAO.Access;

import DAO.JDBCDriver;
import DAO.MySQLSupport;
import Model.MClass;

import java.sql.SQLException;
import java.util.List;

public class ClassHandle extends AbsSQLAccess<MClass>{
    @Override
    // check lại hộ em nhe
    public Boolean INSERT(MClass item) {
String sql = "INSERT INTO `MClass`(grand_id,ID_MANAGER) VALUES ('"+item.getGrandID()+"','"+item.getManagerID()+"'),";

try {
    boolean a = JDBCDriver.SetQuery(sql);
    System.out.println("thêm dữ .iệu thành công "+a);
    return true;
}catch (SQLException e){
    throw new RuntimeException(e);
}
    }

    @Override
    public List<MClass> SELECT(String sql) throws SQLException {

        return null;
    }

    @Override
    public Boolean UPDATE(MClass item) {

        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
