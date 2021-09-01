package dto;

public class DepartmentDto {

    private int departmentId;

    private String departmentName;

    private String description;

    private String officialEmail;

    private String division;

    private int capacity;

    public DepartmentDto(){ }

    public DepartmentDto(int departmentId, String departmentName, String description, String officialEmail, String division, int capacity){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.officialEmail = officialEmail;
        this.division = division;
        this.capacity = capacity;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "\ndepartmentId : " + departmentId +
                "\ndepartmentName : " + departmentName +
                "\ndescription : " + description +
                "\nofficialEmail : " + officialEmail +
                "\ndivision : " + division +
                "\ncapacity : " + capacity+ "\n";
    }
}
