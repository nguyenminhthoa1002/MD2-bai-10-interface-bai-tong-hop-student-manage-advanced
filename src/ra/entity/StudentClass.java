package ra.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentClass implements IStudentManagement {
    private String classID;
    private String className;
    private String descriptions;
    private String classStatus;

    static List<String> listClassStatusManagement = new ArrayList<>();

    public StudentClass() {
    }

    public StudentClass(String classID, String className, String descriptions, String classStatus) {
        this.classID = classID;
        this.className = className;
        this.descriptions = descriptions;
        this.classStatus = classStatus;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public static void classStatusManage() {
        int countClassStatus = 1;
        listClassStatusManagement.add("Active");
        listClassStatusManagement.add("Inactive");
        listClassStatusManagement.add("Not open yet");
        for (String listClassStatus : listClassStatusManagement) {
            System.out.printf("%d. %s\n", countClassStatus, listClassStatus);
            countClassStatus++;
        }
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Enter Class ID: ");
        do {
            this.classID = scanner.nextLine();
            if (this.classID.trim().length() == 5) {
                if (this.classID.charAt(0) == 'J') {
                    break;
                } else {
                    System.err.println("Please enter Class ID start with J");
                }
            } else {
                System.err.println("Please enter Class ID with 5 characters!");
            }
        } while (true);
        System.out.println("Enter Class Name: ");
        do {
            this.className = scanner.nextLine();
            if (this.className.trim().length() <= 10) {
                break;
            } else {
                System.err.println("Please enter Class Name no more than 10 characters!");
            }
        } while (true);
        System.out.println("Enter Class Descriptions: ");
        this.descriptions = scanner.nextLine();
        System.out.println("Choose Class Status: ");
        System.out.println("1. Active");
        System.out.println("2. Inactive");
        System.out.println("3. Not open yet");
        System.out.println("Your choice is: ");
        do {
            int choiceClassStatus = Integer.parseInt(scanner.nextLine());
            switch (choiceClassStatus){
                case 1:
                    this.classStatus = "Active";
                    return;
                case 2:
                    this.classStatus = "Inactive";
                    return;
                case 3:
                    this.classStatus = "Not open yet";
                    return;
                default:
                    System.err.println("This Class Status doesn't exist! Please try again");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("%-15s%-20s%-40s%-15s\n",this.classID,this.className,this.descriptions,this.classStatus);
    }
}
