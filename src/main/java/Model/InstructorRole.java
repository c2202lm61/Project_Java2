package Model;

public class InstructorRole {
    private int ID;
    private String role_name;
    private int Role_id;
    private int ID_NUMBER;

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public InstructorRole(int ID, String role_name) {
        this.ID = ID;
        this.role_name = role_name;
    }

    public InstructorRole(int ID, int role_id, int ID_NUMBER) {
        this.ID = ID;
        Role_id = role_id;
        this.ID_NUMBER = ID_NUMBER;
    }

    public InstructorRole(int role_id, int ID_NUMBER) {
        Role_id = role_id;
        this.ID_NUMBER = ID_NUMBER;
    }

    public InstructorRole() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRole_id() {
        return Role_id;
    }

    public void setRole_id(int role_id) {
        Role_id = role_id;
    }

    public int getID_NUMBER() {
        return ID_NUMBER;
    }

    public void setID_NUMBER(int ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }
}
