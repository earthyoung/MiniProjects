import service.StudentService;

public class MainApplication {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        studentService.run();

    }

}
