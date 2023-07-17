package DAO.Access;

import DAO.JDBCDriver;
import Model.Block;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantHandle extends AbsSQLAccess<Block>{
    public GrantHandle(){
        table = "grants";
    }
    @Override
    public Boolean INSERT(Block item) {
        return null;
    }

    @Override
    public List<Block> SELECT(String sql) throws SQLException {
        List<Block> a = new ArrayList<>();

        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            Block b = new Block();
            b.setID(resultSet.getInt("ID"));
            b.setName(resultSet.getString("name"));
            a.add(b);
        }
        return a;
    }

    @Override
    public Boolean UPDATE(Block item) {
        return null;
    }

    @Override
    public Boolean DELETE(int id) {
        return null;
    }
}
