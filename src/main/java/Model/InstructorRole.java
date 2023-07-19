package Model;

public class InstructorRole {
    private int tclass_id;
    private int Role_id;
    private int ID_NUMBER;

    public void setID_NUMBER (int ID_NUMBER){this.ID_NUMBER =ID_NUMBER;}
    public void setRole_id (int Role_id){this.Role_id = Role_id;}
    public void setTclass_id(int Tclass_id){this.tclass_id = tclass_id;}

    public int getID_NUMBER (){return this.ID_NUMBER;}
    public int getRole_id (){return this.Role_id ;}
    public int getTclass_id(){return this.tclass_id ;}
}
