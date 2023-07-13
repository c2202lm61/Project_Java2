package Model;

import java.sql.Date;

public class Student {
    public int ID;
    public String Name;
    public boolean  Gender;
    public Date Birhday;
    public String Address;
    public int ClassID;
    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return this.ID;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String  getName(){
        return this.Name;
    }
    public void setGender(Boolean Gender){
        this.Gender =  Gender;
    }
    public Boolean getGender(){
        return this.Gender;
    }
    public void setBirthday(Date Birthday){
        this.Birhday = Birthday;
    }
    public Date getBirthday(){
        return this.Birhday;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getAddress(){
        return this.Address;
    }

    public Date getBirhday() {
        return Birhday;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public int getClassID() {
        return ClassID;
    }
}
