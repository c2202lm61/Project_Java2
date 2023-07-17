package Model;

import java.sql.Date;

public class Instructor {

    private int ID_NUMBER;
    private String name;
    private Date birthday;
    private  Boolean gender;
    private  String password;

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

    public void  setGender(Boolean gender){this.gender = gender;}
    public  Boolean getGender(){
        return this.gender;
    }

    public  void setPassword(String password){this.password = password;}
    public String getPassword(){
        return this.password;
    }

}
