package Model;

public class Score {
    public int StudentID;
    public int SubjectID;
    public double ScoreValue;

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setScoreValue(double scoreValue) {
        ScoreValue = scoreValue;
    }

    public double getScoreValue() {
        return ScoreValue;
    }

    public void setSubjectID(int subjectID) {
        SubjectID = subjectID;
    }

    public int getSubjectID() {
        return SubjectID;
    }
}
