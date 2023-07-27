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


    public int getMaDiem(){return this.MaDiem;}
    public void setMaDiem(int MaDiem){
        this.MaDiem = MaDiem;
    }

    public  int getMaHocSinh(){return this.MaHocSinh;}
    public void setMaHocSinh(int MaHocSinh){
        this.MaHocSinh = MaHocSinh;
    }

    public String getTenHS(){return this.TenHS;}
    public void setTenHS(String TenHS){
        this.TenHS = TenHS;
    }

    public int getMon(){return  this.Mon;}
    public void setMon(int mon){
        this.Mon = mon;
    }

    public float getDHS1(){return this.DHS1;}
    public void setDHS1(float DHS1){
        this.DHS1 = DHS1;
    }

    public float getDHS2(){return  this.DHS2;}
    public void setDHS2(float DHS2){
        this.DHS2 = DHS2;
    }
    public  float getDHS3(){return  this.DHS3;}
    public void setDHS3(float DHS3){
        this.DHS3 = DHS3;
    }
    public  float getDHS4(){return  this.DHS4;}
    public void setDHS4(float DHS4){
        this.DHS4 = DHS4;
    }

    public float getTongDien() {
        return TongDien;
    }

    public void setTongDien(){
        this.TongDien = (this.DHS1 + 2*this.DHS2 + 3*this.DHS3 + 4*this.DHS4)/10;
    }



}
