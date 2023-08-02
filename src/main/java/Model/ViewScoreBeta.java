package Model;

public class ViewScoreBeta implements Name {
    private int studentID;
    private String studentName;
    private int  classCode;
    private int grantID;
    private int subjectCode;
    private double scoreValue;
    private int scoreType;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    @Override
    public String getName() {
        return studentName;
    }

    public void setName(String studentName) {
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

}
