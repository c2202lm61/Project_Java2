package Model;

import java.time.LocalDate;

public class Instructor {

    private int ID_NUMBER;
    private String name;
    private LocalDate birthday;
    private  Boolean gender;
    private  String password;
    private  String Email;
    private  String Phone;

    public void setID_NUMBER(int ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }

    public int getID_NUMBER(){
        return this.ID_NUMBER;
    }

    public void setName(String name){ this.name = name;}
    public String getName(){
        return this.name;
    }


    public  void  setBirthday(LocalDate birthday){this.birthday = birthday;}
    public  LocalDate getBirthday(){
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
    public  void setEmail(String Email){this.Email = Email;}
    public String getEmail(){
        return this.Email;
    }
    public  void setPhone(String Phone){this.Phone=Phone;}
    public String getPhone(){
        return this.Phone;
    }


}
