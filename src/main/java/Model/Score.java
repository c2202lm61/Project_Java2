package Model;

public class Score {
    public int StudentSubjectID;
    public int TypeScoreID;
    public double ScoreValue;

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
