package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.Access.ScoreStudentHandle;
import DAO.JDBCDriver;

public class ViewScore {
    private int MaDiem;
    private int MaHocSinh;

    private int MaKhoi;
    private String TenHS;
    private int Mon;
    private float DHS1;
    private float DHS2;
    private float DHS3;
    private float DHS4;
    private float TongDien;
    public void setMaDiem(int MaDiem){
        this.MaDiem = MaDiem;
    }
    public void setMaKhoi(int MaKhoi){
            this.MaKhoi = MaKhoi;
        }
    public void setMaHocSinh(int MaHocSinh){
        this.MaHocSinh = MaHocSinh;
    }
    public void setTenHS(String TenHS){
        this.TenHS = TenHS;
    }
    public void setMon(int mon){
        this.Mon = mon;
    }
    public void setDHS1() throws SQLException{
        float total = 0;
        int count = 0;
        String sql ="SELECT ScoreValue FROM score_student WHERE `ss_id` = "+this.MaDiem+" AND `ts_id` = 1";

        List<Float> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        this.DHS1 = total/count;
    }
    public void setDHS2()throws SQLException{
        float total = 0;
        int count = 0;
        String sql ="SELECT ScoreValue FROM score_student WHERE `ss_id` = "+this.MaDiem+" AND `ts_id` = 2";

        List<Float> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        this.DHS2 = total/count;
    }
    public void setDHS3()throws SQLException{
        float total = 0;
        int count = 0;
        String sql ="SELECT ScoreValue FROM score_student WHERE `ss_id` = "+this.MaDiem+" AND `ts_id` = 3";

        List<Float> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        this.DHS3 = total/count;
    }
    public void setDHS4(float DHS4)throws SQLException{
        float total = 0;
        int count = 0;
        String sql ="SELECT ScoreValue FROM score_student WHERE `ss_id` = "+this.MaDiem+" AND `ts_id` = 4";

        List<Float> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        this.DHS1 = total/count;
    }
    public void setTongDien(){
        this.TongDien = (this.DHS1 + 2*this.DHS2 + 3*this.DHS3 + 4*this.DHS4)/10;
    }


}
