package Model;

public class ViewScoreBeta {
    private int studentID;
    private String studentName;
    private int  classCode;
    private int grantID;
    private int subjectCode;
    private double scoreValue;
    private int scoreType;
    private String scoreType2;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public int getGrantID() {
        return grantID;
    }

    public void setGrantID(int grantID) {
        this.grantID = grantID;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setScoreValue(double scoreValue) {
        this.scoreValue = scoreValue;
    }

    public double getScoreValue() {
        return scoreValue;
    }

    public void setScoreType(int scoreType) {
        this.scoreType = scoreType;
    }

    public int getScoreType() {
        return scoreType;
    }

    public String getScoreType2() {
        return scoreType2;
    }

    public void setScoreType2(String scoreType2) {
        this.scoreType2 = scoreType2;
    }
}
