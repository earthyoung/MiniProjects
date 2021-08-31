package dto;

public class StudentDto {

    private int studentId;

    private String name;

    private String grade;

    private int departmentId;

    private String registered;

    public StudentDto(){

    }

    public StudentDto(int studentId, String name, String grade, int departmentId, String registered){
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.departmentId = departmentId;
        this.registered = registered;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "studentId : " + studentId +
                "\nname : " + name +
                "\ngrade : " + grade +
                "\ndepartmentId : " + departmentId +
                "\nregistered : " + registered + "\n";
    }

    /*
    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", departmentId=" + departmentId +
                ", registered='" + registered + '\'' +
                '}';
    }
    */


}
