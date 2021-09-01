package dao;

import dto.DepartmentDto;
import dto.StudentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class StudentDao {

    public List<StudentDto> searchAllStudent() throws SQLException {
        List<StudentDto> student = new ArrayList<>();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/example",
                "root",
                "earthyoung"
        );

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM students ORDER BY name ASC");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int studentId = rs.getInt("ID");
            String name = rs.getString("name");
            String grade = rs.getString("grade");
            int departmentId = rs.getInt("department_id");
            String registered = rs.getString("registered");

            student.add(new StudentDto(studentId, name, grade, departmentId, registered));
        }
        conn.close();

        return student;
    }

    public void addStudent(StudentDto studentDto) throws SQLException {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/example",
                "root",
                "earthyoung"
        );
        PreparedStatement ps = conn.prepareStatement("INSERT INTO students (ID, name, grade, department_id, registered) VALUES(?, ?, ?, ?, ?)");
        Object[] columns = {Integer.valueOf(studentDto.getStudentId()), studentDto.getName(), studentDto.getGrade(), Integer.valueOf(studentDto.getDepartmentId()), studentDto.getRegistered()};

        for(int i=0 ; i<columns.length ; i++) {
            if (!(checkColumns((i + 1), columns[i]))) {
                cleanUp(conn, ps, "students");
            }
        }
        ps.setInt(1, studentDto.getStudentId());
        ps.setString(2, studentDto.getName());
        ps.setString(3, studentDto.getGrade());
        ps.setInt(4, studentDto.getDepartmentId());
        ps.setString(5, studentDto.getRegistered());

        ps.executeUpdate();
        conn.close();
    }

    public void copyCoding() throws SQLException {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/example",
                "root",
                "earthyoung"
        );

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM students ORDER BY name ASC");

        ResultSet rs = ps.executeQuery();
        ps.clearParameters();

        while(rs.next()){

            int studentId = rs.getInt("ID");
            String name = rs.getString("name");
            String grade = rs.getString("grade");
            int departmentId = rs.getInt("department_id");
            String registered = rs.getString("registered");

            String encodedStudentId = Integer.toHexString(studentId);
            String encodedName = new String(encoding(name));
            String encodedGrade = new String(encoding(grade));
            String encodedDepartmentId = Integer.toHexString(departmentId);
            String encodedRegistered = new String(encoding(registered));

            Object[] encodedColumn = {encodedStudentId, encodedName, encodedGrade, encodedDepartmentId, encodedRegistered};

            ps = conn.prepareStatement("INSERT INTO code (ID, name, grade, department_id, registered) VALUES(?, ?, ?, ?, ?)");

            for(int i=0 ; i<encodedColumn.length ; i++){
                if(!(checkColumns((i+1), encodedColumn[i]))){
                    cleanUp(conn, ps, "code");
                }
            }
            ps.executeUpdate();
            ps.clearParameters();

            encodedName = null;  encodedGrade = null; encodedRegistered = null;
        }
        conn.close();
    }

    public List<StudentDto> takeOut() throws SQLException {

        List<StudentDto> student = new ArrayList<>();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/example",
                "root",
                "earthyoung"
        );

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM code");

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int studentId = Integer.parseInt(rs.getString("ID"), 16);
            String name = new String(decoding(rs.getString("name")));
            String grade = new String(decoding(rs.getString("grade")));
            int departmentId = Integer.parseInt(rs.getString("department_id"), 16);
            String registered = new String(decoding(rs.getString("registered")));

            student.add(new StudentDto(studentId, name, grade, departmentId, registered));
            name = null; grade = null; registered = null;
        }
        conn.close();

        return student;
    }

    public List<DepartmentDto> showAllDepartments() throws SQLException {
        List<DepartmentDto> departments = new ArrayList<>();

        Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3307/example",
          "root",
          "earthyoung"
        );

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM department");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int departmentId = rs.getInt("department_id");
            String departmentName = rs.getString("name");
            String description = rs.getString("description");
            String officialEmail = rs.getString("official_email");
            String division = rs.getString("division");
            int capacity = rs.getInt("capacity");

            departments.add(new DepartmentDto(departmentId, departmentName, description, officialEmail, division, capacity));
        }
        conn.close();

        return departments;
    }

    private byte[] encoding(String str){
        return Base64.getEncoder().encode(str.getBytes());
    }

    private byte[] decoding(String str){
        return Base64.getDecoder().decode(str.getBytes());
    }

    private void wrongRequest(){
        System.out.println("WRONG REQUEST");
        System.exit(0);
    }

    private void cleanUp(Connection conn, PreparedStatement ps, String tbName) throws SQLException {
        ps.clearParameters();
        ps = conn.prepareStatement("DELETE BOTTOM(1) FROM ?");
        ps.setString(1, tbName);
        ps.executeUpdate();
        ps.clearParameters();
        conn.close();
        wrongRequest();
    }

    public boolean checkColumns(int colNum, Object obj){
        if(colNum==1) return checkStudentId(obj);
        if(colNum==2) return checkName(obj);
        if(colNum==3) return checkGrade(obj);
        if(colNum==4) return checkDepartmentId(obj);
        if(colNum==5) return checkRegistered(obj);
        else return false;
    }

    private boolean checkStudentId(Object obj){
        if(obj.getClass().getName().equals("java.lang.Integer")) return true;
        return false;
    }

    private boolean checkName(Object obj){
        if(obj.getClass().getName().equals("java.lang.String")) return true;
        return false;
    }

    private boolean checkGrade(Object obj){
        if(obj.getClass().getName().equals("java.lang.String")) return true;
        return false;
    }

    private boolean checkDepartmentId(Object obj){
        if(obj.getClass().getName().equals("java.lang.Integer")) return true;
        return false;
    }

    private boolean checkRegistered(Object obj){
        if(obj.getClass().getName().equals("java.lang.String")) return true;
        return false;
    }

}
