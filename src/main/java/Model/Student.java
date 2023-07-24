package Model;

import java.time.LocalDate;

public class Student {
    private int ID;

    private String Name;

    private String SocialSecurtyNumber;
    private boolean  Gender;
    private LocalDate Birhday;
    private String Phone;

    private String Address;
    private int ClassID;





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

    public void setPhone(String Phone){
        this.Phone = Phone;
    }
    public String  getPhone(){
        return this.Phone;
    }

    public void setSocialSecurtyNumber(String SocialSecurtyNumber){
        this.SocialSecurtyNumber =  SocialSecurtyNumber;
    }
    public String getSocialSecurtyNumber(){
        return this.SocialSecurtyNumber;
    }
    public void setGender(Boolean Gender){
        this.Gender =  Gender;
    }
    public Boolean getGender(){
        return this.Gender;
    }
    public void setBirthday(LocalDate Birthday){
        this.Birhday = Birthday;
    }
    public LocalDate getBirthday(){
        return this.Birhday;
    }
    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getAddress(){
        return this.Address;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public int getClassID() {
        return ClassID;
    }
}
