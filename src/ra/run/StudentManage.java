package ra.run;

import ra.entity.Student;
import ra.entity.StudentClass;

import java.util.*;

public class StudentManage {
    static List<StudentClass> listStudentClass = new ArrayList<>();
    static List<Student> listStudent = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int choiceUpdateSubject;

    static boolean statusClassManage = true;
    static boolean statusStudentManage = true;

    public static void main(String[] args) {
        do {
            System.out.println("**********************QUẢN LÝ HỌC VIỆN*******************");
            System.out.println("1. Quản lý lớp");
            System.out.println("2. Quản lý sinh viên");
            System.out.println("3. Thoát");
            System.out.println("Your choice is: ");
            int choiceAcademyManage = Integer.parseInt(scanner.nextLine());
            switch (choiceAcademyManage) {
                case 1:
                    StudentManage.classManagement(scanner);
                    break;
                case 2:
                    StudentManage.studentManagement(scanner);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.err.println("Please choose 1-3");
            }
        } while (true);
    }

    public static void classManagement(Scanner scanner) {
        do {
            System.out.println("**********************QUẢN LÝ LỚP HỌC********************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp học đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            System.out.println("Your choice is: ");
            int choiceClassManage = Integer.parseInt(scanner.nextLine());
            switch (choiceClassManage) {
                case 1:
                    addNewClass(scanner);
                    break;
                case 2:
                    updateClass(scanner);
                    break;
                case 3:
                    displayClass();
                    break;
                case 4:
                    listClassIsActive();
                    break;
                case 5:
                    searchClassByName(scanner);
                    break;
                case 6:
                    statusClassManage = false;
                    break;
                default:
                    System.err.println("Please choose 1 - 6");
            }
        } while (statusClassManage);
    }

    public static void studentManagement(Scanner scanner) {
        do {
            System.out.println("***********************QUẢN LÝ SINH VIÊN******************");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viên");
            System.out.println("3. Hiển thị thông tin sinh viên");
            System.out.println("4. Tính điểm trung bình");
            System.out.println("5. Xếp loại sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình và yếu");
            System.out.println("9. Thống kê các sinh viên Pass qua các môn học");
            System.out.println("10.Thoát");
            System.out.println("Your choice is: ");
            int choiceStudentManage = Integer.parseInt(scanner.nextLine());
            switch (choiceStudentManage) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    displayStudent();
                    break;
                case 4:
                    calAvgMarkListStudent();
                    break;
                case 5:
                    classifyStudent();
                    break;
                case 6:
                    sortStudentByAvgMarkASC();
                    break;
                case 7:
                    searchStudentByName(scanner);
                    break;
                case 8:
                    statisticStudent();
                    break;
                case 9:
                    statisticStudentPassExam();
                    break;
                case 10:
                    statusStudentManage = false;
                    break;
                default:
                    System.err.println("Please choose 1-10");
            }
        } while (statusStudentManage);
    }

    public static void addNewClass(Scanner scanner) {
        System.out.println("Enter the number of Class you want to input: ");
        int numberInputClass = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberInputClass; i++) {
            System.out.printf("Input Information Class %d\n", i + 1);
            StudentClass studentClass = new StudentClass();
            studentClass.inputData(scanner,listStudentClass,listStudent);
            listStudentClass.add(studentClass);
        }
    }

    public static void updateClass(Scanner scanner) {
        System.out.println("Enter Class Id to update information: ");
        String updateClassID = scanner.nextLine();
        int indexClassUpdate = -1;
        for (int i = 0; i < listStudentClass.size(); i++) {
            if (listStudentClass.get(i).getClassID().equals(updateClassID)) {
                indexClassUpdate = i;
                break;
            }
        }

        if (indexClassUpdate != -1) {
            System.out.println("Enter Class Name Update: ");
            String updatedClassName = scanner.nextLine();
            if (updatedClassName != "" && updatedClassName.length() != 0 && updatedClassName.trim().length() <= 10) {
                listStudentClass.get(indexClassUpdate).setClassName(updatedClassName);
            }
            System.out.println("Enter Class Descriptions Update: ");
            String updatedClassDescriptions = scanner.nextLine();
            if (updatedClassDescriptions != "" && updatedClassDescriptions.length() != 0) {
                listStudentClass.get(indexClassUpdate).setDescriptions(updatedClassDescriptions);
            }
            System.out.println("Choose Class Status Update: ");
            System.out.println("1. Active");
            System.out.println("2. Inactive");
            System.out.println("3. Not open yet");
            System.out.println("Your choice is: ");
            do {
                int choiceClassStatusUpdate = Integer.parseInt(scanner.nextLine());
                if (choiceClassStatusUpdate == 1) {
                    listStudentClass.get(indexClassUpdate).setClassStatus("Active");
                    break;
                } else if (choiceClassStatusUpdate == 2) {
                    listStudentClass.get(indexClassUpdate).setClassStatus("Inactive");
                    break;
                } else if (choiceClassStatusUpdate == 3) {
                    listStudentClass.get(indexClassUpdate).setClassStatus("Not open yet");
                    break;
                } else {
                    System.err.println("Please choose 1 or 2");
                }
            } while (true);
        } else {
            System.err.println("This Class doesn't exist! Please try again");
        }
    }

    public static void displayClass() {
        System.out.println("****************************** Class Information *********************************");
        System.out.printf("%-15s%-20s%-40s%-15s\n", "Class Id", "Class Name", "Descriptions", "Class status");
        for (StudentClass listClass : listStudentClass) {
            listClass.displayData();
        }
    }

    public static void listClassIsActive() {
        System.out.println("****************************** Class Information *********************************");
        System.out.printf("%-15s%-20s%-40s%-15s\n", "Class Id", "Class Name", "Descriptions", "Class status");
        for (int i = 0; i < listStudentClass.size(); i++) {
            if (listStudentClass.get(i).getClassStatus().equals("Active")) {
                listStudentClass.get(i).displayData();
            }
        }
    }

    public static void searchClassByName(Scanner scanner) {
        System.out.println("Enter Class Name to Search: ");
        String searchClass = scanner.nextLine();
        boolean checkExitClass = false;
        System.out.println("****************************** Class Information *********************************");
        System.out.printf("%-15s%-20s%-40s%-15s\n", "Class Id", "Class Name", "Descriptions", "Class status");
        for (int i = 0; i < listStudentClass.size(); i++) {
            if (listStudentClass.get(i).getClassName().toLowerCase().contains(searchClass.toLowerCase())) {
                listStudentClass.get(i).displayData();
                checkExitClass = true;
            }
        }
        if (!checkExitClass) {
            System.err.println("This Class doesn't exist");
        }
    }

    public static void addNewStudent(Scanner scanner) {
        System.out.println("Enter the number of Student you want to input: ");
        int numberInputStudent = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberInputStudent; i++) {
            System.out.printf("Input Information Student %d\n", i + 1);
            Student studentNew = new Student();
            studentNew.inputData(scanner,listStudentClass,listStudent);
            System.out.println("Choose Class for Student: ");
            int count = 1;
            for (StudentClass listClass : listStudentClass) {
                System.out.printf("%d. %s\n", count, listClass.getClassName());
                count++;
            }
            System.out.println("Your choice is: ");
            int choice = Integer.parseInt(scanner.nextLine());
            studentNew.setStudentClass(listStudentClass.get(choice - 1));
            listStudent.add(studentNew);
        }
    }

    public static void updateStudent(Scanner scanner) {
        if (listStudent.size() == 0) {
            System.err.println("List Student is empty now! Please input new Student");
        }
        System.out.println("Enter Student Id to update information: ");
        String updateStudentID = scanner.nextLine();
        for (Student student : listStudent) {
            if (student.getStudentID().equals(updateStudentID)) {
                System.out.println("Enter Student Name Update: ");
                String updateStudentName = "";
                do {
                    updateStudentName = scanner.nextLine();
                    if (updateStudentName != "" && updateStudentName.trim().length() != 0) {
                        if (updateStudentName.trim().length() >= 10 && updateStudentName.trim().length() <= 50) {
                            student.setStudentName(updateStudentName);
                            break;
                        } else {
                            System.err.println("Please enter Student name from 10 to 50 characters!");
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Enter Student Age Update: ");
                String updateStudentAge = "";
                do {
                    updateStudentAge = scanner.nextLine();
                    if (updateStudentAge != "" && updateStudentAge.trim().length() != 0) {
                        if (Integer.parseInt(updateStudentAge) >= 18) {
                            student.setAge(Integer.parseInt(updateStudentAge));
                            break;
                        } else {
                            System.err.println("Please enter Student Age rather or equal to 18!");
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Enter Student Gender: ");
                String gender = scanner.nextLine();
                if (gender != "" && gender.trim().length() != 0) {
                    student.setGender(Boolean.parseBoolean(gender));
                }
                System.out.println("Choose Class Update for student: ");
                int countClass = 1;
                for (StudentClass studentClass : listStudentClass) {
                    System.out.printf("%d. %s\n", countClass, studentClass.getClassName());
                    countClass++;
                }
                System.out.printf("%d. No Update Class\n", countClass);
                System.out.println("Your choice is: ");
                int choiceClassUpdate = Integer.parseInt(scanner.nextLine());
                if (choiceClassUpdate != countClass) {
                    student.setStudentClass(listStudentClass.get(choiceClassUpdate - 1));
                }
                System.out.println("Choose subject you want to update Mark: ");
                System.out.println("1. Java Script");
                System.out.println("2. Java Core");
                System.out.println("3. Java Web");
                System.out.println("4. Exit");
                System.out.println("Your choice is: ");
                boolean checkUpdateMark = true;

                do {
                    choiceUpdateSubject = Integer.parseInt(scanner.nextLine());
                    switch (choiceUpdateSubject) {
                        case 1:
                            updateStudentMark(student.getListMarkJavaScript(), scanner, student, "Java Script");
                            checkUpdateMark=false;
                            break;
                        case 2:
                            updateStudentMark(student.getListMarkJavaCore(), scanner, student, "Java Core");
                            checkUpdateMark=false;
                            break;
                        case 3:
                            updateStudentMark(student.getListMarkJavaWeb(), scanner, student, "Java Web");
                            checkUpdateMark = false;
                            break;
                        case 4:
                            checkUpdateMark = false;
                            break;
                        default:
                            System.err.println("Please choose 1 - 4");
                    }
                } while (checkUpdateMark);
                student.calAvgMark();
                student.classifyByGPA();
                System.out.println("Enter Student Status: ");
                String studentStatus = scanner.nextLine();
                if (studentStatus != "" && studentStatus.trim().length()!=0) {
                    student.setStudentStatus(Boolean.parseBoolean(studentStatus));
                }
            }
        }
    }

    public static void updateStudentMark(List<Float> listMark, Scanner scanner, Student student, String subject) {
        boolean check = true;
        do {
            System.out.printf("Update %s Mark for Student\n", subject);
            System.out.println("1. INPUT new mark");
            System.out.println("2. ADD new mark");
            System.out.println("3. UPDATE mark");
            System.out.println("4. No update");
            System.out.println("Your choice is: ");
            int choiceUpdateMark = Integer.parseInt(scanner.nextLine());
            switch (choiceUpdateMark) {
                case 1:
                    List<Float> newMark = new ArrayList<>();
                    Student.inputMark(newMark, scanner);
                    switch (subject){
                        case "Java Script":
                            student.setListMarkJavaScript(newMark);
                            break;
                        case "Java Core":
                            student.setListMarkJavaCore(newMark);
                            break;
                        case "Java Web":
                            student.setListMarkJavaWeb(newMark);
                            break;
                    }
                    break;
                case 2:
                    switch (subject) {
                        case "Java Script":
                            Student.inputMark(student.getListMarkJavaScript(), scanner);
                            break;
                        case "Java Core":
                            Student.inputMark(student.getListMarkJavaCore(), scanner);
                            break;
                        case "Java Web":
                            Student.inputMark(student.getListMarkJavaWeb(), scanner);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter the Exam times you want to update: ");
                    boolean checkExamTimes = true;
                    int examTimes;
                    do {
                        examTimes = Integer.parseInt(scanner.nextLine());
                        if (examTimes<=0 && examTimes>listMark.size()) {
                            checkExamTimes = true;
                            System.out.printf("Exam times %d doesn't exist");
                        } else {
                            checkExamTimes = false;
                        }
                    } while (checkExamTimes);
                    if (!checkExamTimes) {
                        System.out.println("Enter Update Mark: ");
                        float updateMark = Float.parseFloat(scanner.nextLine());
                        switch (subject) {
                            case "Java Script":
                                student.getListMarkJavaScript().set(examTimes - 1, updateMark);
                                break;
                            case "Java Core":
                                student.getListMarkJavaCore().set(examTimes - 1, updateMark);
                                break;
                            case "Java Web":
                                student.getListMarkJavaWeb().set(examTimes - 1, updateMark);
                                break;
                        }
                    }
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    System.err.println("Please choose 1 - 4");
            }
        } while (check);
    }

    public static void displayStudent() {
        System.out.println("****************************** Student Information *********************************");
        System.out.printf("%-15s%-30s%-15s%-15s%-20s%-15s%-20s%-20s\n", "Student ID", "Student Name", "Age", "Gender", "Class", "AvgMark", "GPA", "Student Status");
        for (Student listStudent : listStudent) {
            listStudent.displayData();
        }
    }

    public static void calAvgMarkListStudent() {
        for (Student student : listStudent) {
            student.calAvgMark();
        }
        System.out.println("All Average Mark have been calculated!");
    }

    public static void classifyStudent() {
        for (Student listStudent : listStudent) {
            listStudent.classifyByGPA();
        }
        System.out.println("All Student have been classified!");
    }

    public static void sortStudentByAvgMarkASC() {
        Collections.sort(listStudent, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o1.getAverageMark() - o2.getAverageMark());
            }
        });
        System.out.println("****************************** Student Information *********************************");
        System.out.printf("%-15s%-30s%-15s%-15s%-20s%-15s%-20s%-20s\n", "Student ID", "Student Name", "Age", "Gender", "Class", "AvgMark", "GPA", "Student Status");
        for (Student listStudent : listStudent) {
            listStudent.displayData();
        }
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Enter Student Name to Search: ");
        String searchStudent = scanner.nextLine();
        boolean checkStudentSearch = false;
        System.out.println("****************************** Student Information *********************************");
        System.out.printf("%-15s%-30s%-15s%-15s%-20s%-15s%-20s%-20s\n", "Student ID", "Student Name", "Age", "Gender", "Class", "AvgMark", "GPA", "Student Status");
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getStudentName().toLowerCase().contains(searchStudent.toLowerCase())) {
                listStudent.get(i).displayData();
                checkStudentSearch = true;
            }
        }
        if (!checkStudentSearch) {
            System.err.println("This Student doesn't exist");
        }
    }

    public static void statisticStudent() {
        int countExcellent = 0, countGood = 0, countAverage = 0, countWeak = 0;
        for (Student listStudent : listStudent) {
            if (listStudent.getAverageMark() < 5) {
                countWeak++;
            } else if (listStudent.getAverageMark() < 7) {
                countAverage++;
            } else if (listStudent.getAverageMark() < 9) {
                countGood++;
            } else {
                countExcellent++;
            }
        }
        System.out.printf("%-30s%-30s%-30s%-30s\n", "Excellent Student", "Good Student", "Average Student", "Weak Student");
        System.out.printf("%-30d%-30d%-30d%-30d\n", countExcellent, countGood, countAverage, countWeak);
    }

    public static void statisticStudentPassExam() {
        int countPassExam = 0;
        for (Student listStudent : listStudent) {
            int jsSize = listStudent.getListMarkJavaScript().size();
            int jcSize = listStudent.getListMarkJavaCore().size();
            int jwSize = listStudent.getListMarkJavaWeb().size();
            if (listStudent.getAverageMark() >= 5 &&
                    listStudent.getListMarkJavaScript().get(jsSize - 1) >= 5 &&
                    listStudent.getListMarkJavaCore().get(jcSize - 1) >= 5 &&
                    listStudent.getListMarkJavaWeb().get(jwSize - 1) >= 5) {
                countPassExam++;
            }
        }
        System.out.printf("Number of Student Pass the exam: %d\n", countPassExam);
    }
}
