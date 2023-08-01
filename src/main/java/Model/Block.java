package Model;

public class Block implements Name{
    private int ID;
    private String GrandID;


    private String Name;



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
}
