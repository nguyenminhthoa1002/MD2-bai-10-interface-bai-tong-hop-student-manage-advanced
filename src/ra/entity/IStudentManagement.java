package ra.entity;

import java.util.List;
import java.util.Scanner;

public interface IStudentManagement {
    static final float MARK_PASS = 5F;
    void inputData(Scanner scanner, List<StudentClass> listStudentClass, List<Student> listStudent);
    void displayData();
}
