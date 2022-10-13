package ra.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements IStudentManagement{
    private String studentID;
    private String studentName;
    private int age;
    private boolean gender;
    private StudentClass studentClass;
    private List<Float> listMarkJavaScript = new ArrayList<>();
    private List<Float> listMarkJavaCore = new ArrayList<>();
    private List<Float> listMarkJavaWeb = new ArrayList<>();
    private float averageMark;
    private String GPA;
    private boolean studentStatus;

    public Student() {
    }

    public Student(String studentID, String studentName, int age, boolean gender, StudentClass studentClass, List<Float> listMarkJavaScript, List<Float> listMarkJavaCore, List<Float> listMarkJavaWeb, float averageMark, String GPA, boolean studentStatus) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.gender = gender;
        this.studentClass = studentClass;
        this.listMarkJavaScript = listMarkJavaScript;
        this.listMarkJavaCore = listMarkJavaCore;
        this.listMarkJavaWeb = listMarkJavaWeb;
        this.averageMark = averageMark;
        this.GPA = GPA;
        this.studentStatus = studentStatus;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public List<Float> getListMarkJavaScript() {
        return listMarkJavaScript;
    }

    public void setListMarkJavaScript(List<Float> listMarkJavaScript) {
        this.listMarkJavaScript = listMarkJavaScript;
    }

    public List<Float> getListMarkJavaCore() {
        return listMarkJavaCore;
    }

    public void setListMarkJavaCore(List<Float> listMarkJavaCore) {
        this.listMarkJavaCore = listMarkJavaCore;
    }

    public List<Float> getListMarkJavaWeb() {
        return listMarkJavaWeb;
    }

    public void setListMarkJavaWeb(List<Float> listMarkJavaWeb) {
        this.listMarkJavaWeb = listMarkJavaWeb;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public boolean isStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Enter Student ID: ");
        do {
            this.studentID = scanner.nextLine();
            if (this.studentID.trim().length() == 6) {
                if (this.studentID.charAt(0) == 'S') {
                    break;
                } else {
                    System.err.println("Please enter Student ID start with S");
                }
            } else {
                System.err.println("Please enter Student ID with 6 characters!");
            }
        } while (true);
        System.out.println("Enter Student Name: ");
        do {
            this.studentName = scanner.nextLine();
            if (this.studentName.trim().length()>=10 && this.studentName.trim().length()<=50) {
                break;
            } else {
                System.err.println("Please enter Student name from 10 to 50 characters!");
            }
        } while (true);
        System.out.println("Enter Student Age: ");
        do {
            this.age = Integer.parseInt(scanner.nextLine());
            if (this.age >=18) {
                break;
            } else {
                System.err.println("Please enter Student Age rather or equal to 18!");
            }
        } while (true);
        System.out.println("Enter Student Gender: ");
        this.gender = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Enter Java Script Score: ");
        inputMark(listMarkJavaScript, scanner);
        System.out.println("Enter Java Core Score: ");
        inputMark(listMarkJavaCore,scanner);
        System.out.println("Enter Java Web Score: ");
        inputMark(listMarkJavaWeb,scanner);
        System.out.println("Enter Student Status: ");
        this.studentStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    public static void inputMark(List<Float> listMark, Scanner scanner) {
        int count = 1;
        int countAsk = 2;
        do {
            System.out.printf("Input Mark %d: ", count);
            listMark.add(Float.parseFloat(scanner.nextLine()));
            count++;
            System.out.printf("Do you want to input Mark %d\n", countAsk);
            countAsk++;
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Your choice is: ");
            int choiceMark = Integer.parseInt(scanner.nextLine());
            if (choiceMark != 1) {
                break;
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("%-15s%-30s%-15d%-15b%-20s%-15f%-20s%-20b\n", this.studentID, this.studentName, this.age, this.gender, this.studentClass.getClassName(),this.averageMark,this.GPA,this.studentStatus);
        System.out.println("JavaScript Mark: ");
        for (Float js_mark : listMarkJavaScript) {
            System.out.printf("%f\t",js_mark);
        }
        System.out.printf("\n");
        System.out.println("JavaCore Mark: ");
        for (Float javaCore_mark : listMarkJavaCore) {
            System.out.printf("%f\t",javaCore_mark);
        }
        System.out.printf("\n");
        System.out.println("JavaWeb Mark: ");
        for (Float javaWeb_mark : listMarkJavaWeb) {
            System.out.printf("%f\t",javaWeb_mark);
        }
        System.out.printf("\n");
    }

    public void calAvgMark(){
        this.averageMark = (listMarkJavaScript.get(listMarkJavaScript.size() - 1) +
                listMarkJavaCore.get(listMarkJavaCore.size() - 1) +
                listMarkJavaWeb.get(listMarkJavaWeb.size() - 1)) / 3;
    }

    public void classifyByGPA() {
        if (this.averageMark<5) {
            this.GPA = "Yếu";
        } else if (this.averageMark<7) {
            this.GPA = "Trung bình";
        } else if (this.averageMark<9) {
            this.GPA = "Khá";
        } else {
            this.GPA = "Giỏi";
        }
    }
}
