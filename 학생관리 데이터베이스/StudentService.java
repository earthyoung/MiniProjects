package service;

import dao.StudentDao;
import dto.DepartmentDto;
import dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    StudentDao studentDao = new StudentDao();
    StudentDto studentDto = new StudentDto();
    DepartmentDto departmentDto = new DepartmentDto();
    List<StudentDto> students = new ArrayList<>();
    List<DepartmentDto> departments = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private static String[] choices = {
            "===============수행할 작업을 선택하세요===============",
            "---------------1. 모든 학생 조회하기-----------------",
            "---------------2. 현재 학과 조회하기-----------------",
            "---------------3. 새로운 학생 추가하기---------------",
            "---------------4. 종료하기--------------------------",
            "***************************************************"
    };

    public void run(){

        while(true){
            showChoices();
            int choice = scanner.nextInt();
            System.out.println("---------------" + choice + "번을 선택했습니다--------------------");
            if (choice == 4) {  break;  }

            switch(choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    showAllDepartments();
                    break;
                case 3:
                    addNewStudent();
                    break;
                default:
                    scanner.close();
                    return;
            }
        }
        scanner.close();
    }

    public void showChoices(){
        for(int i=0 ; i<choices.length ; i++){
            System.out.println(choices[i]);
        }
    }

    private void showAllStudents(){
        try{
            students = studentDao.searchAllStudent();
            System.out.println(students);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void showAllDepartments(){
        try{
            departments = studentDao.showAllDepartments();
            System.out.println(departments);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void addNewStudent(){
        try{
            System.out.println("===============추가하려는 학생의 정보를 입력해주세요===============");
            System.out.print("---------------학번 : ");
            int studentId = scanner.nextInt();
            studentDto.setStudentId(studentId);
            System.out.print("---------------이름 : ");
            String name = scanner.next();
            studentDto.setName(name);
            System.out.print("---------------성적 : ");
            String grade = scanner.next();
            studentDto.setGrade(grade);
            System.out.print("---------------학과 번호 : ");
            int departmentId = scanner.nextInt();
            studentDto.setDepartmentId(departmentId);
            System.out.print("---------------등록 여부 [YES/NO/EXCHANGE] : ");
            String registered = scanner.next();
            studentDto.setRegistered(registered);

            studentDao.addStudent(studentDto);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
