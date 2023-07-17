package Model;

import java.sql.Date;

public class Instructor {

    public int ID_NUMBER;
    public String name;
    public Date birthday;
    public  String gender;
    public  String password;

    public void setID_NUMBER(int ID){
        this.ID_NUMBER= ID_NUMBER;
    }
    public int getID_NUMBER(){
        return this.ID_NUMBER;
    }

    public void setName(String name){ this.name = name;}
    public String getName(){
        return this.name;
    }

    public  void  setBirthday(Date birthday){this.birthday = birthday;}
    public  Date getBirthday(){
        return this.birthday;
    }

    public void  setGender(String gender){this.gender = gender;}
    public  String getGender(){
        return this.gender;
    }

    public  void setPassword(String password){this.password = password;}
    public String getPassword(){
        return this.password;
    }

}
