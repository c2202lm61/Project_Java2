package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.Access.ScoreStudentHandle;
import DAO.JDBCDriver;

public class ViewScore {
    private int MaDiem;
    private String TenHS;
    private int MaHocSinh;
    private int MaLop;
    private int MaKhoi;

    private int MaMon;



    private float DHS1;
    private float DHS2;
    private float DHS3;
    private float DHS4;
    private float TongDien;

    public void setMaLop(int Malop){
        this.MaLop = Malop;
    }
    public int getMaLop(){
        return this.MaLop;
    }
    public int getMaDiem(){return this.MaDiem;}
    public void setMaDiem(int MaDiem){
        this.MaDiem = MaDiem;
    }

    public  int getMaHocSinh(){return this.MaHocSinh;}

    public void setMaKhoi(int MaKhoi){
            this.MaKhoi = MaKhoi;
    }
    public  int getMaKhoi(){return this.MaKhoi;}

    public void setMaHocSinh(int MaHocSinh){
        this.MaHocSinh = MaHocSinh;
    }

    public String getTenHS(){return this.TenHS;}
    public void setTenHS(String TenHS){
        this.TenHS = TenHS;
    }

    public int getMaMon(){return  this.MaMon;}
    public void setMaMon(int mon){
        this.MaMon = mon;
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
    public void setDHS4()throws SQLException{
        float total = 0;
        int count = 0;
        String sql ="SELECT ScoreValue FROM score_student WHERE `ss_id` = "+this.MaDiem+" AND `ts_id` = 4";

        List<Float> a = new ArrayList<>();
        final ResultSet resultSet = JDBCDriver.ExecQuery(sql);
        while (resultSet.next()){
            total += resultSet.getInt("ScoreValue");
            count+=1;
        }
        this.DHS4 = total/count;
    }

    public float getDHS1(){return this.DHS1;}


    public float getDHS2(){return  this.DHS2;}

    public  float getDHS3(){return  this.DHS3;}

    public  float getDHS4(){return  this.DHS4;}

    public void setTongDien(){
        this.TongDien = (DHS1 + 2*DHS2 + 3*DHS3 + 4*DHS4)/10;
    }

    public float getTongDien() {
            return TongDien;
    }


}
