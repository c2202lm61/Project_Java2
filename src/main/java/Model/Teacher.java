package Model;

import java.util.Date;

public class Teacher implements Name {
    private int ID;
    private String Name;
    private Boolean Gender;
    private Date Birthday;
    private String Address;
    private String PhoneNumber;
    private int SubjectID;
    private int ClassID;





    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String getName() {
        return Name;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setSubjectID(int subjectID) {
        SubjectID = subjectID;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public int getClassID() {
        return ClassID;
    }
}
