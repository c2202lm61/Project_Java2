package Model;

public class Score {
    private int StudentSubjectID;
    private int TypeScoreID;
    private double ScoreValue;



    public void setStudentSubjectID(int StudentSubjectID) {
        this.StudentSubjectID = StudentSubjectID;
    }

    public int getStudentSubjectID() {
        return StudentSubjectID;
    }

    public void setScoreValue(double scoreValue) {
        ScoreValue = scoreValue;
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
