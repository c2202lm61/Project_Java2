package Model;

public class Score {

    private int ScoreID;
    private int StudentSubjectID;
    private int TypeScoreID;
    private double ScoreValue;


    public void setScoreID(int ScoreID) {
        this.ScoreID = ScoreID;
    }
    public int getScoreID() {
       return this.ScoreID;
    }
    public void setStudentSubjectID(int StudentSubjectID) {
        this.StudentSubjectID = StudentSubjectID;
    }

    public int getStudentSubjectID() {
        return StudentSubjectID;
    }

    public void setScoreValue(double scoreValue) {
        this.ScoreValue = scoreValue;
    }

    public double getScoreValue() {
        return ScoreValue;
    }

    public void setTypeScoreID(int TypeScoreID) {
        this.TypeScoreID = TypeScoreID;
    }

    public int getTypeScoreID() {
        return TypeScoreID;
    }
}
