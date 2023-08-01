package Model;

public class Subject implements Name {
    private int ID;
    private String Name;

    private int Credits;
    private int GrandID;



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
    public void setCredits(int Credits) {
        this.Credits = Credits;
    }

    public int getCredits() {
        return this.Credits;
    }

    public void setGrandID(int GrandID){
        this.GrandID = GrandID;
    }
    public int getGrandID(){return this.GrandID;}
}
