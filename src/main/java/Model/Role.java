package Model;

public class Role implements Name{
    private int id;
    private String name;
    private Boolean student_mngs;
    private Boolean teach_mngs;



    private Boolean block_mngs;
    private Boolean class_mngs;
    private Boolean subject_mngs;
    private Boolean asignment_mngs;
    private Boolean typescore_mngs;
    private Boolean role_mngs;
    private Boolean position_mngs;
    public Role(String name, Boolean student_mngs, Boolean teach_mngs, Boolean block_mngs, Boolean class_mngs, Boolean subject_mngs, Boolean asignment_mngs, Boolean typescore_mngs, Boolean role_mngs, Boolean position_mngs) {
        this.name = name;
        this.student_mngs = student_mngs;
        this.teach_mngs = teach_mngs;
        this.block_mngs = block_mngs;
        this.class_mngs = class_mngs;
        this.subject_mngs = subject_mngs;
        this.asignment_mngs = asignment_mngs;
        this.typescore_mngs = typescore_mngs;
        this.role_mngs = role_mngs;
        this.position_mngs = position_mngs;
    }

    public Role(int id, String name, Boolean student_mngs, Boolean teach_mngs, Boolean block_mngs, Boolean class_mngs, Boolean subject_mngs, Boolean asignment_mngs, Boolean typescore_mngs, Boolean role_mngs, Boolean position_mngs) {
        this.id = id;
        this.name = name;
        this.student_mngs = student_mngs;
        this.teach_mngs = teach_mngs;
        this.block_mngs = block_mngs;
        this.class_mngs = class_mngs;
        this.subject_mngs = subject_mngs;
        this.asignment_mngs = asignment_mngs;
        this.typescore_mngs = typescore_mngs;
        this.role_mngs = role_mngs;
        this.position_mngs = position_mngs;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStudent_mngs() {
        return student_mngs;
    }

    public void setStudent_mngs(Boolean student_mngs) {
        this.student_mngs = student_mngs;
    }

    public Boolean getTeach_mngs() {
        return teach_mngs;
    }

    public void setTeach_mngs(Boolean teach_mngs) {
        this.teach_mngs = teach_mngs;
    }

    public Boolean getBlock_mngs() {
        return block_mngs;
    }

    public void setBlock_mngs(Boolean block_mngs) {
        this.block_mngs = block_mngs;
    }

    public Boolean getClass_mngs() {
        return class_mngs;
    }

    public void setClass_mngs(Boolean class_mngs) {
        this.class_mngs = class_mngs;
    }

    public Boolean getSubject_mngs() {
        return subject_mngs;
    }

    public void setSubject_mngs(Boolean subject_mngs) {
        this.subject_mngs = subject_mngs;
    }

    public Boolean getAsignment_mngs() {
        return asignment_mngs;
    }

    public void setAsignment_mngs(Boolean asignment_mngs) {
        this.asignment_mngs = asignment_mngs;
    }

    public Boolean getTypescore_mngs() {
        return typescore_mngs;
    }

    public void setTypescore_mngs(Boolean typescore_mngs) {
        this.typescore_mngs = typescore_mngs;
    }

    public Boolean getRole_mngs() {
        return role_mngs;
    }

    public void setRole_mngs(Boolean role_mngs) {
        this.role_mngs = role_mngs;
    }

    public Boolean getPosition_mngs() {
        return position_mngs;
    }

    public void setPosition_mngs(Boolean position_mngs) {
        this.position_mngs = position_mngs;
    }
}
