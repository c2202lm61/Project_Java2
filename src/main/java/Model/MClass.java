package Model;

public class MClass {
    private int ID;
    private int GrandID;

    private int ManagerID;
    public void setID(int ID){
        this.ID= ID;
    }
    public int getID(){
        return this.ID;
    }
    public void setGrandID(int GrandID){
        this.GrandID = GrandID;
    }
    public int getGrandID(){
        return this.GrandID;
    }
    public void setManagerID(int ManagerID){
        this.ManagerID = ManagerID;
    }
    public int getManagerID(){
        return this.ManagerID;
    }
}
