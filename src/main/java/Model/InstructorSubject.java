package Model;

public class InstructorSubject {
    private int ID_Teach;
    private int ID_NUMBER;
    private int Subject_code;



    public void setID_NUMBER(int ID_NUMBER){this.ID_NUMBER = ID_NUMBER;}
    public void setID_Teach(int ID_Teach){this.ID_Teach = ID_Teach;}
    public void setSubject_code(int Subject_code){this.Subject_code = Subject_code;}

    public int getID_NUMBER(){return this.ID_NUMBER ;}
    public int getID_Teach(){return this.ID_Teach ;}
    public int getSubject_code(){return this.Subject_code ;}
}
