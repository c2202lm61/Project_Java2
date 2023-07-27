package Model;

public class ViewScore {
    private int MaDiem;
    private int MaHocSinh;
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

    public void setMaHocSinh(int MaHocSinh){
        this.MaHocSinh = MaHocSinh;
    }
    public void setTenHS(String TenHS){
        this.TenHS = TenHS;
    }
    public void setMon(int mon){
        this.Mon = mon;
    }
    public void setDHS1(float DHS1){
        this.DHS1 = DHS1;
    }
    public void setDHS2(float DHS2){
        this.DHS2 = DHS2;
    }
    public void setDHS3(float DHS3){
        this.DHS3 = DHS3;
    }
    public void setDHS4(float DHS4){
        this.DHS4 = DHS4;
    }
    public void setTongDien(){
        this.TongDien = (this.DHS1 + 2*this.DHS2 + 3*this.DHS3 + 4*this.DHS4)/10;
    }



}
