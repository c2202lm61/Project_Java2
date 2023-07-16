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
    public void INSERT(Block item) {

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
    public void UPDATE(Block item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
