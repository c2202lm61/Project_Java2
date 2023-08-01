package Model;

public class TypeScore implements Name {

    private int id;
    private String Name;

    
    public void setID(int ID){
        this.id= ID;
    }
    public int getID(){
        return this.id;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    @Override
    public String getName(){
        return this.Name;
    }


}
