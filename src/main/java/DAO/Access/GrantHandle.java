package DAO.Access;

import Model.Block;

import java.util.List;

public class GrantHandle extends AbsSQLAccess<Block>{
    protected GrantHandle(String table) {
        super(table);
    }

    @Override
    public void INSERT(Block item) {

    }

    @Override
    public List<Block> SELECT(String sql) {
        return null;
    }

    @Override
    public void UPDATE(Block item) {

    }

    @Override
    public void DELETE(int id) {

    }
}
